/*
 * Copyright 2016 Matthew Rogers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package utils;

import java.util.LinkedList;
import java.util.List;

/**
 * <strong>Project:</strong> Firestorm
 * <strong>File:</strong> ThreadPool.java
 *
 * @author Matthew Rogers
 */
public class ThreadPool extends ThreadGroup {

    private static IDAssigner poolID = new IDAssigner(1);

    private boolean alive;
    private List<Runnable> taskQueue;
    private int id;

    public ThreadPool(int numThreads) {
        super("ThreadPool-" + poolID.next());
        this.id = poolID.getCurrentID();
        setDaemon(true);
        taskQueue = new LinkedList<Runnable>();
        alive = true;
        for (int i = 0; i < numThreads; i++)
            new PooledThread(this).start();
    }

    public synchronized void runTask(Runnable task) {
        if (!alive) throw new IllegalStateException("ThreadPool-" + id + " is dead");
        if (task != null) {
            taskQueue.add(task);
            notify();
        }
    }
    
    public synchronized void close(){
        if(!alive) return;
        alive = false;
        taskQueue.clear();
        interrupt();
    }
    
    public void join(){
        synchronized(this){
            alive = false;
            notifyAll();
        }
        
        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);
        
        for(int i = 0; i < count; i++){
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                //Logger.getLogger(ThreadPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected synchronized Runnable getTask() throws InterruptedException {
        while (taskQueue.size() == 0) {
            if (!alive) return null;
            wait();
        }
        return taskQueue.remove(0);
    }

}
