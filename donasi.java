static boolean donasi(int nomorRekening) {
    int saldoTotal;
    int indexNasabah;
    int nomorRekening;
    int notaDonasi;

    System.out.print("===== MENU DONASI =====\n");
    System.out.print("Masukan nomor rekening: ");
    nomorRekening = input.nextInt();
    input.nextLine();

    // cek jika sudah terdapat nomor rekening ini
    indexNasabah = cariDataNasabah(nomorRekening);

    if (indexNasabah == -1) {
      System.out.println("\n===== Nomor Rekening Tidak Ditemukan =====");

      enterUntukMelanjutkan();

      return false;
    } 

    else {
        System.out.print("Masukan Jumlah Donasi: ");
        int donasi = input.nextInt();

        saldoTotal = dataNasabah[indexNasabah][1];
    
    if (saldoTotal > jumlahDonasi) {
        
        System.out.println("\n===== Sisa Saldo Anda "+ saldoTotal +"=====");
        
        enterUntukMelanjutkan();

      return true;
    } else {
      System.out.print("\n===== Saldo Anda Tidak Mencukupi =====");

      enterUntukMelanjutkan();

      return false;
  }
}