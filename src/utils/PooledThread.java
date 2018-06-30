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

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <strong>Project:</strong> Firestorm
 * <strong>File:</strong> PooledThread.java
 *
 * @author Matthew Rogers
 */
public class PooledThread extends Thread {

    private static IDAssigner threadID = new IDAssigner(1);

    private ThreadPool pool;

    public PooledThread(ThreadPool pool) {
        super(pool, "PooledThread-" + threadID.next());
        this.pool = pool;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            Runnable task = null;
            try {
                task = pool.getTask();
            } catch (InterruptedException ex) {
                Logger.getLogger(PooledThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (task == null) return;

            try {
                task.run();
            } catch (Throwable t) {
                pool.uncaughtException(this, t);
            }
        }
    }

}
