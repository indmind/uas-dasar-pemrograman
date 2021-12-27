static int cekSaldo(int nomorRekening) {
    int saldoTotal;
    int indexNasabah;
    int nomorRekening;

    System.out.print("===== MENU CEK SALDO =====\n");
    System.out.print("Masukan nomor rekening: ");
    nomorRekening = input.nextInt();

    input.nextLine();

    // cek jika sudah terdapat nomor rekening ini
    indexNasabah = cariDataNasabah(nomorRekening);

    if (indexNasabah == -1) {
      System.out.println("\n===== Nomor Rekening Tidak Ditemukan =====");

      enterUntukMelanjutkan();

      return 0;
    }

    saldoTotal = dataNasabah[indexNasabah][1];

    if (saldoTotal > 0) {
      System.out.println("\n===== Sisa Saldo Anda "+ saldoTotal +"=====");

      enterUntukMelanjutkan();

      return saldoTotal;
    } else {
      System.out.print("\n===== Saldo Anda Habis =====");

      enterUntukMelanjutkan();

      return saldoTotal;
  }
}