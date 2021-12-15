import java.util.Scanner;

public class Bank {
  static Scanner input = new Scanner(System.in);

  static int dataLength = 1000;

  static int[][] dataNasabah = new int[dataLength][3];
  static String[] namaNasabah = new String[dataLength];
  static char[] genderNasabah = new char[dataLength];

  static String[][] dataTransaksi = new String[dataLength][dataLength];

  static String[] jenisTabungan = {
      "Tabungan Kimchi (tf limit Rp. 5.000.000)",
      "Tabungan Kimbab (tf limit Rp. 20.000.000)"
  };

  static int[] limitTransfer = {
    5000000,
    20000000
};

  static int indexNasabahTerakhir() {
    for (int i = 0; i < dataNasabah.length; i++) {
      if (dataNasabah[i][0] == 0) {
        return i - 1;
      }
    }

    return -1;
  }

  static void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  static void enterUntukMelanjutkan() {
    System.out.println("\nTekan enter untuk melanjutkan...");
    input.nextLine();
  }

  static int tambahkanTransaksi(int indexNasabah, String transaksi) {
    int indexTransaksi = 0;

    for (int i = 0; i < dataTransaksi.length; i++) {
      if (dataTransaksi[indexNasabah][i] == null) {
        indexTransaksi = i;
        break;
      }
    }

    dataTransaksi[indexNasabah][indexTransaksi] = transaksi;

    return indexTransaksi;
  }

  static int inputDataNasabah() {
    int nomorRekening;
    String nama;
    char gender;
    boolean sudahAda;
    int indexBaru;

    System.out.print("===== MENU INPUT DATA NASABAH =====\n");
    System.out.print("Masukan nomor rekening: ");
    nomorRekening = input.nextInt();

    input.nextLine();

    // cek jika sudah terdapat nomor rekening ini
    sudahAda = cariDataNasabah(nomorRekening) != -1;

    if (sudahAda) {
      System.out.println("\n===== Nomor Rekening Sudah Digunakan =====");

      enterUntukMelanjutkan();

      return -1;
    }

    System.out.print("Masukan nama: ");
    nama = input.nextLine();

    System.out.print("Masukan gender (l/p): ");
    gender = input.next().charAt(0);
    input.nextLine();

    indexBaru = indexNasabahTerakhir() + 1;

    dataNasabah[indexBaru][0] = nomorRekening;
    dataNasabah[indexBaru][1] = 0;
    dataNasabah[indexBaru][2] = 0;
    namaNasabah[indexBaru] = nama;
    genderNasabah[indexBaru] = gender;

    System.out.println("\n=====Data nasabah berhasil ditambahkan=====");

    enterUntukMelanjutkan();

    return indexBaru;
  }

  static int cariDataNasabah(int nomorRekening) {
    int indexNasabah = -1;

    for (int i = 0; i < dataNasabah.length; i++) {
      if (dataNasabah[i][0] == nomorRekening) {
        indexNasabah = i;
        break;
      }
    }

    return indexNasabah;
  }

  static void lihatDataNasabah() {
    int indexNasabah, nomorRekening;

    System.out.print("===== MENU LIHAT DATA NASABAH =====\n");
    System.out.print("Masukkan nomor rekening nasabah: ");
    nomorRekening = input.nextInt();
    input.nextLine();

    indexNasabah = cariDataNasabah(nomorRekening);

    clearConsole();

    if (indexNasabah == -1) {
      System.out.println("Nomor rekening nasabah tidak ditemukan");
    } else {
      System.out.println("======= LIHAT DATA NASABAH =======");
      System.out.println("Nama nasabah\t\t: " + namaNasabah[indexNasabah]);
      System.out
          .println("Jenis kelamin nasabah\t: " + (genderNasabah[indexNasabah] == 'l' ? "Laki-laki" : "Perempuan"));
      System.out.println("Saldo nasabah\t\t: Rp. " + dataNasabah[indexNasabah][1]);
      System.out.println("Jenis tabungan\t\t: " + jenisTabungan[dataNasabah[indexNasabah][2]]);
      System.out.println("==================================");
    }

    enterUntukMelanjutkan();
  }

  static boolean pilihJenisTabungan() {
    return false;
  }

  static int checkSaldo() {
    return 0;
  }

  static boolean setorTabungan() {
    int nomorRekening, indexNasabah, jumlahSetoran;

    System.out.print("===== MENU SETOR TABUNGAN =====\n");
    System.out.print("Masukkan nomor rekening nasabah: ");
    nomorRekening = input.nextInt();
    input.nextLine();

    indexNasabah = cariDataNasabah(nomorRekening);

    if (indexNasabah == -1) {
      System.out.println("Nomor rekening nasabah tidak ditemukan");

      enterUntukMelanjutkan();

      return false;
    }

    System.out.print("Masukkan jumlah setoran: ");
    jumlahSetoran = input.nextInt();
    input.nextLine();

    if (jumlahSetoran < 0) {
      System.out.println("Jumlah setoran tidak boleh negatif");

      enterUntukMelanjutkan();

      return false;
    }

    dataNasabah[indexNasabah][1] += jumlahSetoran;

    tambahkanTransaksi(indexNasabah, "Setoran +Rp. " + jumlahSetoran);

    System.out.println("\n===== Setoran tabungan berhasil =====");

    enterUntukMelanjutkan();

    return true;
  }

  static boolean ambilTabungan() {
    return false;
  }

  static boolean transfer() {
    int rekeningAsal, rekeningTujuan, indexRekeningAsal, indexRekeningTujuan, nominalTransfer;

    System.out.print("===== MENU TRANSFER =====\n");

    System.out.print("Masukkan nomor rekening asal: ");
    rekeningAsal = input.nextInt();
    input.nextLine();

    System.out.print("Masukkan nomor rekening tujuan: ");
    rekeningTujuan = input.nextInt();
    input.nextLine();

    indexRekeningAsal = cariDataNasabah(rekeningAsal);
    indexRekeningTujuan = cariDataNasabah(rekeningTujuan);

    if (indexRekeningAsal == -1) {
      System.out.println("Nomor rekening asal tidak ditemukan");

      enterUntukMelanjutkan();

      return false;
    }

    if (indexRekeningTujuan == -1) {
      System.out.println("Nomor rekening tujuan tidak ditemukan");

      enterUntukMelanjutkan();

      return false;
    }

    if (indexRekeningAsal == indexRekeningTujuan) {
      System.out.println("Nomor rekening asal dan tujuan tidak boleh sama");

      enterUntukMelanjutkan();

      return false;
    }

    System.out.print("Masukkan nominal transfer: ");
    nominalTransfer = input.nextInt();
    input.nextLine();

    if (nominalTransfer < 0) {
      System.out.println("Nominal transfer tidak boleh negatif");

      enterUntukMelanjutkan();

      return false;
    }

    if (dataNasabah[indexRekeningAsal][1] < nominalTransfer) {
      System.out.println("Saldo nasabah tidak mencukupi");

      enterUntukMelanjutkan();

      return false;
    }

    if (nominalTransfer > limitTransfer[dataNasabah[indexRekeningTujuan][2]]) {
      System.out.println("Nominal transfer melebihi limit transfer");

      enterUntukMelanjutkan();

      return false;
    }

    dataNasabah[indexRekeningAsal][1] -= nominalTransfer;
    dataNasabah[indexRekeningTujuan][1] += nominalTransfer;

    tambahkanTransaksi(indexRekeningAsal, "Transfer ke " + rekeningTujuan + " -Rp. " + nominalTransfer);
    tambahkanTransaksi(indexRekeningTujuan, "Transfer dari " + rekeningAsal + " +Rp. " + nominalTransfer);

    System.out.println("\n===== Bukti Transfer =====");
    System.out.printf("Nomor rekening asal\t: %d (%s)\n", rekeningAsal, namaNasabah[indexRekeningAsal]);
    System.out.printf("Nomor rekening tujuan\t: %d (%s)\n\n", rekeningTujuan, namaNasabah[indexRekeningTujuan]);
    System.out.println("Nominal transfer\t: Rp. " + nominalTransfer);
    System.out.println("==================================");

    enterUntukMelanjutkan();

    return true;
  }

  static void cetakLaporanTransaksi() {
    int indexNasabah, nomorRekening;

    System.out.print("===== MENU CETAK LAPORAN TRANSAKSI =====\n");
    System.out.print("Masukkan nomor rekening nasabah: ");
    nomorRekening = input.nextInt();
    input.nextLine();

    indexNasabah = cariDataNasabah(nomorRekening);

    if (indexNasabah == -1) {
      System.out.println("Nomor rekening nasabah tidak ditemukan");

      enterUntukMelanjutkan();

      return;
    }

    System.out.println("\n===== LAPORAN TRANSAKSI =====");
    System.out.println("Nama nasabah\t\t: " + namaNasabah[indexNasabah]);
    System.out
        .println("Jenis kelamin nasabah\t: " + (genderNasabah[indexNasabah] == 'l' ? "Laki-laki" : "Perempuan"));
    System.out.println("Saldo nasabah\t\t: Rp. " + dataNasabah[indexNasabah][1]);
    System.out.println("Jenis tabungan\t\t: " + jenisTabungan[dataNasabah[indexNasabah][2]]);
    System.out.println("==================================\n");

    for (int i = dataTransaksi[indexNasabah].length - 1; i >= 0; i--) {
      if (dataTransaksi[indexNasabah][i] != null) {
        System.out.println(dataTransaksi[indexNasabah][i]);
      }
    }

    enterUntukMelanjutkan();
  }

  static boolean donasi() {
    return false;
  }

  public static void main(String[] args) {
    int pilihan;

    do {
      clearConsole();

      System.out.println("===== SELAMAT DATANG =====\n");
      System.out.println("Silahkan pilih menu yang tersedia");
      System.out.println("1. Pendaftaran Nasabah");
      System.out.println("2. Lihat Data Nasabah");
      System.out.println("3. Pilih Jenis Tabungan");
      System.out.println("4. Cek Saldo");
      System.out.println("5. Setor Tabungan");
      System.out.println("6. Ambil Tabungan");
      System.out.println("7. Transfer");
      System.out.println("8. Cetak Laporan Transaksi");
      System.out.println("9. Donasi");
      System.out.println("10. Keluar");
      System.out.println("\n===========================\n");

      System.out.print("Masukkan pilihan anda: ");
      pilihan = input.nextInt();

      clearConsole();

      switch (pilihan) {
        case 1:
          inputDataNasabah();
          break;
        case 2:
          lihatDataNasabah();
          break;
        case 3:
          pilihJenisTabungan();
          break;
        case 4:
          checkSaldo();
          break;
        case 5:
          setorTabungan();
          break;
        case 6:
          ambilTabungan();
          break;
        case 7:
          transfer();
          break;
        case 8:
          cetakLaporanTransaksi();
          break;
        case 9:
          donasi();
          break;
        case 10:
          System.out.println("Terima kasih telah menggunakan aplikasi ini");
          break;
        default:
          System.out.println("Pilihan yang anda masukkan salah");
      }

      System.out.println();
    } while (pilihan != 10);
  }
}
