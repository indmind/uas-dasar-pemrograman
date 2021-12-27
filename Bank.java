import java.util.Random;
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

  static String[] daftarHadiah = {
      "Mobil",
      "Motor",
      "Sepeda",
      "Payung",
      "Karpet",
      "Kaos",
      "Kemeja",
      "Baju",
      "Celana",
      "Topi",
      "Jaket",
      "Sepatu",
      "Bus",
      "Kereta",
      "Tiket bus",
      "Tiket kereta",
      "Tiket pesawat",
      "Kapal",
      "Kapal perahu",
      "Kapal laut",
      "Umroh",
      "Hotel",
      "Haji",
      "Kambing",
      "Kuda",
      "Sapi"
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

  static int tambahkanHistory(int indexNasabah, String transaksi) {
    int indexTransaksi = 0;

    for (int i = 0; i < dataTransaksi[0].length; i++) {
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

    sudahAda = cariDataNasabah(nomorRekening) != -1;

    if (sudahAda) {
      System.out.println("\n==== Nomor Rekening Sudah Digunakan =====");

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

    int indexNasabah;
    int jenisTabungan;
    int nomorRekening;

    System.out.print("===== MENU PILIH JENIS TABUNGAN =====\n");
    System.out.print("Masukan nomor rekening: ");
    nomorRekening = input.nextInt();

    input.nextLine();

    indexNasabah = cariDataNasabah(nomorRekening);

    if (indexNasabah == -1) {
      System.out.println("\n===== Nomor Rekening Tidak Ditemukan =====");

      enterUntukMelanjutkan();

      return false;
    }

    System.out.println("0 = Tabungan Kimchi (tf limit Rp. 5.000.000)");
    System.out.println("1 = Tabungan Kimbab (tf limit Rp. 20.000.000)");

    System.out.print("Masukan Jenis Tabungan: ");
    jenisTabungan = input.nextInt();
    input.nextLine();

    if (jenisTabungan == 0 || jenisTabungan == 1) {

      dataNasabah[indexNasabah][2] = jenisTabungan;

      System.out.println("\n=====Jenis Tabungan Berhasil Diubah====");

      enterUntukMelanjutkan();

      return true;
    } else {

      System.out.print("\n=====Jenis Tabungan Tidak Ditemukan====");

      enterUntukMelanjutkan();

      return false;
    }
  }

  static int cekSaldo() {
    int saldoTotal;
    int indexNasabah;
    int nomorRekening;

    System.out.print("===== MENU CEK SALDO =====\n");
    System.out.print("Masukan nomor rekening: ");
    nomorRekening = input.nextInt();

    input.nextLine();

    indexNasabah = cariDataNasabah(nomorRekening);

    if (indexNasabah == -1) {
      System.out.println("\n===== Nomor Rekening Tidak Ditemukan =====");

      enterUntukMelanjutkan();

      return 0;
    }

    saldoTotal = dataNasabah[indexNasabah][1];

    if (saldoTotal > 0) {
      System.out.println("\n===== Sisa Saldo Anda " + saldoTotal + "=====");

      enterUntukMelanjutkan();

      return saldoTotal;
    } else {
      System.out.print("\n===== Saldo Anda Habis =====");

      enterUntukMelanjutkan();

      return saldoTotal;
    }
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

    tambahkanHistory(indexNasabah, "Setoran +Rp. " + jumlahSetoran);

    System.out.println("\n===== Setoran tabungan berhasil =====");

    enterUntukMelanjutkan();

    return true;
  }

  static boolean ambilTabungan() {
    int nomorRekening, indexNasabah, jumlahAmbilTabungan;

    System.out.print("===== MENU AMBIL TABUNGAN =====\n");
    System.out.print("Masukkan nomor rekening nasabah: ");
    nomorRekening = input.nextInt();
    input.nextLine();
    indexNasabah = cariDataNasabah(nomorRekening);

    if (indexNasabah == -1) {
      System.out.println("Nomor rekening nasabah tidak ditemukan");

      enterUntukMelanjutkan();

      return false;
    }

    System.out.print("Masukkan jumlah ambil: ");
    jumlahAmbilTabungan = input.nextInt();
    input.nextLine();

    if (dataNasabah[indexNasabah][1] < jumlahAmbilTabungan) {
      System.out.println("Saldo anda tidak cukup");
      enterUntukMelanjutkan();
      return false;
    }
    dataNasabah[indexNasabah][1] -= jumlahAmbilTabungan;

    tambahkanHistory(indexNasabah, "Ambil Tabungan -Rp. " + jumlahAmbilTabungan);

    System.out.println("\n===== Ambil tabungan berhasil =====");

    enterUntukMelanjutkan();

    return true;
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

    if (nominalTransfer > limitTransfer[dataNasabah[indexRekeningAsal][2]]) {
      System.out.println("Nominal transfer melebihi limit transfer");
      System.out.println("Jenis Tabungan: " + jenisTabungan[dataNasabah[indexRekeningAsal][2]]);

      enterUntukMelanjutkan();

      return false;
    }

    dataNasabah[indexRekeningAsal][1] -= nominalTransfer;
    dataNasabah[indexRekeningTujuan][1] += nominalTransfer;

    tambahkanHistory(indexRekeningAsal, "Transfer ke " + rekeningTujuan + " -Rp. " + nominalTransfer);
    tambahkanHistory(indexRekeningTujuan, "Transfer dari " + rekeningAsal + " +Rp. " + nominalTransfer);

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
    int saldoTotal;
    int indexNasabah;
    int nomorRekening;

    System.out.print("===== MENU DONASI =====\n");
    System.out.print("Masukan nomor rekening: ");
    nomorRekening = input.nextInt();
    input.nextLine();

    indexNasabah = cariDataNasabah(nomorRekening);

    if (indexNasabah == -1) {
      System.out.println("\n===== Nomor Rekening Tidak Ditemukan =====");

      enterUntukMelanjutkan();

      return false;
    }

    System.out.print("Masukan Jumlah Donasi: ");
    int jumlahDonasi = input.nextInt();
    input.nextLine();

    saldoTotal = dataNasabah[indexNasabah][1];

    if (jumlahDonasi <= saldoTotal) {

      dataNasabah[indexNasabah][1] -= jumlahDonasi;

      saldoTotal = dataNasabah[indexNasabah][1];

      System.out.println("\n===== Berhasil Donasi Sisa Saldo Anda " + saldoTotal + " =====");

      enterUntukMelanjutkan();

      tambahkanHistory(indexNasabah, "Donasi sejumlah -Rp. " + jumlahDonasi);

      return true;
    } else {
      System.out.print("\n===== Saldo Anda Tidak Mencukupi =====");

      enterUntukMelanjutkan();

      return false;
    }
  }

  static void undiHadiah() {
    int indexNasabahTerakhir;

    int indexNasabah;
    int indexHadiah;

    int nomorRekening;
    String nama;
    String hadiah;

    Random random = new Random();

    indexNasabahTerakhir = indexNasabahTerakhir();

    if (indexNasabahTerakhir == -1) {
      System.out.println("\n===== Tidak Ada Nasabah Yang Terdaftar =====");

      enterUntukMelanjutkan();

      return;
    }

    System.out.print("\n===== Undian Hadiah Bulan ini =====\n");

    enterUntukMelanjutkan();

    indexNasabah = random.nextInt(indexNasabahTerakhir + 1);
    indexHadiah = random.nextInt(daftarHadiah.length);

    nomorRekening = dataNasabah[indexNasabah][0];
    nama = namaNasabah[indexNasabah];
    hadiah = daftarHadiah[indexHadiah];

    System.out.println("\n===== Selamat Kepada!! =====");
    System.out.println("Nomor Rekening\t: " + nomorRekening);
    System.out.println("Nama\t\t: " + nama);

    System.out.println("\n\n===== Hadiah Yang Didapatkan =====");
    System.out.println("\n\n" + hadiah + "\n\n");
    System.out.println("==================================");

    enterUntukMelanjutkan();
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
      System.out.println("10. Undi Hadiah Bulanan");
      System.out.println("11. Keluar");
      System.out.println("\n===========================\n");

      System.out.print("Masukkan pilihan anda: ");
      pilihan = input.nextInt();
      input.nextLine();

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
          cekSaldo();
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
          undiHadiah();
          break;
        case 11:
          System.out.println("Terima kasih telah menggunakan aplikasi ini");
          break;
        default:
          System.out.println("Pilihan yang anda masukkan salah");
      }

      System.out.println();
    } while (pilihan != 11);
  }
}
