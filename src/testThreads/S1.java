/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testThreads;

/**
 *
 * @author Muzaffar
 */
class AffableThread extends Thread {

    @Override
    public void run() //Этот метод будет выполнен в побочном потоке
    {
        System.out.println("Привет из побочного потока!");
    }
}

public class S1 {

    static AffableThread mSecondThread;

    public static void main(String[] args) {
        mSecondThread = new AffableThread();	//Создание потока
        mSecondThread.start();					//Запуск потока

        System.out.println("Главный поток завершён...");
    }
}
