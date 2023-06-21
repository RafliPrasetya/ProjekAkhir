package FinalProjek;

import java.util.Scanner;

// Kelas Node untuk menyimpan informasi reservasi
class Node {
    String name; // Nama pelanggan
    String time; // Waktu reservasi
    String date; // Tanggal reservasi
    int tableNumber; // Nomor meja
    Node next; // Pointer ke node berikutnya

    // Konstruktor untuk inisialisasi node
    public Node(String name, String time, String date, int tableNumber) {
        this.name = name;
        this.time = time;
        this.date = date;
        this.tableNumber = tableNumber;
        this.next = null;
    }
}

// Kelas LinkedList untuk menyimpan daftar reservasi
class LinkedList {
    Node head; // Pointer ke node pertama dalam linked list

    // Konstruktor untuk inisialisasi linked list
    public LinkedList() {
        this.head = null;
    }

    // Metode untuk memeriksa apakah linked list kosong
    public boolean isEmpty() {
        return head == null;
    }

    // Metode untuk menambahkan node baru ke linked list
    public void append(String name, String time, String date, int tableNumber) {
        Node newNode = new Node(name, time, date, tableNumber);
        if (isEmpty()) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Metode untuk menampilkan daftar reservasi
    public void display() {
        if (isEmpty()) {
            System.out.println("Linked list kosong!");
        } else {
            Node current = head;
            while (current != null) {
                System.out.println("Nama: " + current.name);
                System.out.println("Jam: " + current.time);
                System.out.println("Tanggal: " + current.date);
                System.out.println("Nomer meja: " + current.tableNumber);
                System.out.println();
                current = current.next;
            }
        }
    }
}

// Kelas Queue untuk menyimpan daftar tunggu
class Queue {
    private String[] items; // Array untuk menyimpan item dalam antrian
    private int front; // Indeks depan antrian
    private int rear; // Indeks belakang antrian
    private int size; // Jumlah item dalam antrian
    private int capacity; // Kapasitas maksimum antrian

    // Konstruktor untuk inisialisasi antrian
    public Queue(int capacity) {
        this.capacity = capacity;
        this.items = new String[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Metode untuk memeriksa apakah antrian kosong
    public boolean isEmpty() {
        return size == 0;
    }

    // Metode untuk memeriksa apakah antrian penuh
    public boolean isFull() {
        return size == capacity;
    }

    // Metode untuk menambahkan item ke antrian
    public void enqueue(String item) {
        if (isFull()) {
            System.out.println("Antrian penuh. Tidak dapat mengantri.");
            return;
        }
        rear = (rear + 1) % capacity;
        items[rear] = item;
        size++;
    }

    // Metode untuk mengeluarkan item dari antrian
    public String dequeue() {
        if (isEmpty()) {
            System.out.println("Antrian kosong. Tidak dapat mengeluarkan item.");
            return null;
        }
        String dequeuedItem = items[front];
        front = (front + 1) % capacity;
        size--;
        return dequeuedItem;
    }

    // Metode untuk mencetak isi antrian
    public void printQueue() {
        if (!isEmpty()) {
            System.out.println("Menampilkan Antrian dari Atas :");
            for (int i = rear; i > -1; i--) {
                System.out.println(items[i]);
            }
        } else {
            System.out.println("Antrian masih kosong");
        }
    }
}

// Kelas Restaurant sebagai pengontrol utama program
class Restaurant {
    LinkedList calledList; // Objek linked list untuk menyimpan nama-nama yang telah dipanggil
    LinkedList reservationList; // Objek linked list untuk menyimpan reservasi
    Queue waitingList; // Objek antrian untuk menyimpan daftar tunggu
    String[] sortedReservations; // Array untuk menyimpan reservasi yang diurutkan

    // Konstruktor untuk inisialisasi objek Restaurant
    public Restaurant() {
        reservationList = new LinkedList();
        waitingList = new Queue(10);
        sortedReservations = new String[10];
        calledList = new LinkedList();
    }

    // Metode untuk membuat reservasi
    public void makeReservation(String name, String time, String date, int tableNumber) {
        reservationList.append(name, time, date, tableNumber);
        System.out.println("Reservasi Berhasil dilakukan!");
    }

    // Metode untuk menampilkan daftar reservasi
    public void displayReservations() {
        System.out.println("Reservasi saat ini:");
        reservationList.display();
    }

    // Metode untuk mencari reservasi berdasarkan nama
    public boolean searchReservation(String name) {
        Node current = reservationList.head;
        while (current != null) {
            if (current.name.equals(name)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Metode untuk menambahkan nama ke daftar tunggu
    public void addWaitingList(String name) {
        waitingList.enqueue(name);
        System.out.println("Ditambahkan ke daftar tunggu!");
    }

    // Metode untuk menampilkan daftar tunggu
    public void displayDaftarTunggu() {
        waitingList.printQueue();
    }

    // Metode untuk memanggil pelanggan dari daftar tunggu
    public void displayWaitingList() {
        System.out.println("Daftar tunggu saat ini:");
        while (!waitingList.isEmpty()) {
            String item = waitingList.dequeue();
            calledList.append(item, "", "", 0);
            System.out.println(item);
        }
    }

    // Metode untuk menampilkan daftar yang telah dipanggil
    public void displayCalledList() {
        System.out.println("Daftar yang telah dipanggil:");
        calledList.display();
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Restaurant restaurant = new Restaurant();

        while (true) {
            System.out.println("\n===== Restaurant Reservation System =====");
            System.out.println("1. Buat Reservasi");
            System.out.println("2. Tampilan Reservasi");
            System.out.println("3. Cari Reservasi");
            System.out.println("4. Tambahkan ke Daftar Tunggu");
            System.out.println("5. Tampilan Daftar Tunggu");
            System.out.println("6. Memanggil Pelanggan di Daftar Tunggu");
            System.out.println("7. Tampilan yang Telah Dipanggil");
            System.out.println("0. Keluar");
            System.out.print("Masukkan Pilihan Anda: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("Terima kasih telah menggunakan Sistem Reservasi Restoran!");
                    return;
                case 1:
                    System.out.print("Masukkan Nama untuk Reservasi: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan Waktu Reservasi: ");
                    String time = scanner.nextLine();
                    System.out.print("Masukkan Tanggal Reservasi: ");
                    String date = scanner.nextLine();
                    System.out.print("Masukkan Nomer Meja: ");
                    int tableNumber = scanner.nextInt();
                    scanner.nextLine();
                    restaurant.makeReservation(name, time, date, tableNumber);
                    break;
                case 2:
                    restaurant.displayReservations();
                    break;
                case 3:
                    System.out.print("Masukkan Nama yang akan dicari: ");
                    String searchName = scanner.nextLine();
                    boolean isReservationFound = restaurant.searchReservation(searchName);
                    if (isReservationFound) {
                        System.out.println("Reservasi Ditemukan!");
                    } else {
                        System.out.println("Reservasi tidak Ditemukan!");
                    }
                    break;
                case 4:
                    System.out.print("Masukkan Nama untuk ditambahkan ke daftar tunggu: ");
                    String waitingName = scanner.nextLine();
                    restaurant.addWaitingList(waitingName);
                    break;
                case 5:
                    restaurant.displayDaftarTunggu();
                    break;
                case 6:
                    String dequeuedItem = restaurant.waitingList.dequeue();
                    if (dequeuedItem != null) {
                        System.out.println("Pelanggan " + dequeuedItem + " dipanggil dari daftar tunggu.");
                    } else {
                        System.out.println("Daftar tunggu kosong. Tidak ada pelanggan yang dipanggil.");
                    }
                    break;
                case 7:
                    restaurant.displayCalledList();
                    break;
                default:
                    System.out.println("Pilihan salah. Silakan coba lagi.");
                    break;
            }
        }
    }
}
