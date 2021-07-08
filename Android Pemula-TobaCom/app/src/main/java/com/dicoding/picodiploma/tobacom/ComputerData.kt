package com.dicoding.picodiploma.tobakomputer

import com.dicoding.picodiploma.tobacom.Computer
import com.dicoding.picodiploma.tobacom.R

object ComputerData {
    private var computerNames = arrayOf(
            "Acer Aspire M564",
            "Acer Nitro",
            "Apple iMac 27",
            "Asus pro D540",
            "Asus Zephorus",
            "HP Omen 15",
            "Lenovo IdeaCentre C460",
            "Lenovo Yoga Slim 7",
            "Macbook Pro",
            "MSI"
    )

    private var computerDetails = arrayOf(
            "Digerakkan oleh catu daya sebesar 300W, komputer seri ini dikomando oleh " +
                    "variasi prosesor Core2 Quad Q 6600/ Q 6700 atau Core2 Duo dari Intel. " +
                    "Chipset yang tersedia adalah NVIDIA GeForce 7100 dan nForce 630i. Kecepatannya disokong memori " +
                    "DDR2 667/800 MHz SDRAM yang dapat dinaikkan hingga 4GB. Media penyimpanan ATA dapat dinaikkan hingga 1TB," +
                    " cukup longgar untuk penggunaan normal. Para penggemar music dapat berbahagia dengan kualitas suara yang dihasilkan " +
                    "oleh sistem audio HD channel 7.1 yang mendukung S/PDIF (Sony/Philips). Kejernihan dan kekuatan suaranya tentunya tak " +
                    "perlu dipermasalahkan. Drive optiknya cukup memberikan kejutan dengan pembakar BD dan SuperMulti, BD/DVD ROM dan SuperMulti" +
                    " berteknologi LabelFlash. Ketajaman visual disokong oleh grafis NVIDIA GeForce 7100 yang terintegrasi.",

            "\n" +
                    "    Tipe : Notebook, Laptop Gaming\n" +
                    "    CPU : AMD, Core i5, Core i7\n" +
                    "    GPU : NVIDIA GeForce GTX 1050, AMD Radeon RX550\n" +
                    "    RAM : Up To 32GB\n" +
                    "    Tipe Penyimpanan : HDD up to 2TB, SSD up to 512GB\n" +
                    "    Layar : 15.6 inchi, FHD 1920x1080 piksel\n" +
                    "    Konektifitas : HDMI, USB 2.0, USB 3.0, USB 3.1, USB Type-C\n",

            "Layar \n" +
                    "\n" +
                    "    Layar Retina 5K\n" +
                    "    Layar Retina 5K 27 inci (diagonal)\n" +
                    "    Resolusi 5120 x 2880 dengan dukungan untuk satu miliar warna\n" +
                    "    Kecerahan 500 nit\n" +
                    "    Warna luas (P3)\n" +
                    "    Teknologi True Tone\n" +
                    "    Dapat dikonfigurasi dengan kaca nano-texture\n" +
                    "\n" +
                    "Prosesor \n" +
                    "\n" +
                    "    3,1 GHz\n" +
                    "    Intel Core i5 generasi ke-10 6‑core 3,1 GHz, Turbo Boost hingga 4,5 GHz\n" +
                    "\n" +
                    "Memori \n" +
                    "\n" +
                    "    3,1 GHz\n" +
                    "    8 GB (dua 4 GB) memori DDR4 2666 MHz; empat slot SO-DIMM, yang dapat diakses pengguna\n" +
                    "\n" +
                    "Penyimpanan1 \n" +
                    "\n" +
                    "    3,1 GHz\n" +
                    "    SSD 256 GB\n" +
                    "    3,3 GHz\n" +
                    "    SSD 512 GB\n" +
                    "    3,8 GHz\n" +
                    "    SSD 512 GB\n" +
                    "\n" +
                    "Grafis \n" +
                    "\n" +
                    "    3,1 GHz\n" +
                    "    Radeon Pro 5300 dengan memori GDDR6 4 GB",

            "ASUSPRO D540MC ditenagai prosesor Intel Core generasi ke-8, Core i5-8400 dengan kecepatan hingga 4 GHz. Prosesor ini memiliki TDP rendah, hanya 65W sehingga konsumsi listrik akan menjadi lebih efisien. Selain itu, dukungan pengolah grafis Nvidia GeForce 1030 dengan VRAM 2 GB juga diharapkan mampu memberikan performa yang lebih baik.\n" +
                    "\n" +
                    "ASUS memadukan prosesor dan pengolah grafis tersebut dengan HDD 1 TB dan RAM 4 GB bertipe DDR4. RAM jenis ini memiliki performa lebih kencang dibanding DDR3 dan konsumsi dayanya juga lebih rendah. Jika dibandingkan dengan DDR3 yang mengonsumsi daya hingga 1,5V per modul, DDR4 dapat bekerja optimal dengan daya 1,2V saja.\n" +
                    "\n" +
                    "Dari sisi software, ASUSPRO D540MC telah menggunakan Windows 10 Pro. Sistem operasi terbaru dari Microsoft ini dapat menunjang jenis bisnis apapun mulai dari usaha kecil menengah maupun korporasi. Windows 10 Pro telah dilengkapi dengan built-in security maupun easy-to-implement management, meningkatkan produktivitas maupun kemudahan untuk melakukan upgrade dan proteksi data.",

            "SPESIFIKASI \tAsus ROG Zephyrus S15 (GX502) \tAsus ROG Zephyrus M15 (GU502)\n" +
                    "Prosesor \tIntel Core i7-10875H \tIntel Core i7-10875H\n" +
                    "RAM \t32GB 3200MHz DDR4 \t16GB 3200MHz DDR4\n" +
                    "Memori Internal \t1TB M.2 NVMe PCIe 3.0 x4 SSD \t1TB M.2 NVMe PCIe 3.0 x4 SSD\n" +
                    "512GB M.2 NVMe PCIe 3.0 x4 SSD\n" +
                    "Layar \t15,6 inci IPS FHD (1920x1080) refresh rate 300Hz \t15,6 inci IPS FHD (1920x1080) refresh rate 240Hz\n" +
                    "GPU \tNVIDIA GeForce RTX 2080 SUPER + ROG Boost\n" +
                    "NVIDIA GeForce RTX 2070 Super + ROG Boost \tNVIDIA GeForce RTX 1660Ti +ROG Boost\n" +
                    "NVIDIA GeForce RTX 2060 + ROG Boost\n" +
                    "NVIDIA GeForce RTX 2070 + ROG Boost\n" +
                    "Bobot \t1,9kg \t1,7kg\n" +
                    "Dimensi \t36 x 25,2 x 1,89cm \t36 x 25,2 1,89cm",

            "CPU\t10th Gen Intel Core i7\n" +
                    "AMD Ryzen 7\n" +
                    "OS\tWindows 10\n" +
                    "Memori\t16 GB RAM\n" +
                    "Penyimpanan\t512 GB PCIe NVMe SSD\n" +
                    "Layar\t15.6 inci, 300 Hz, dan 300 nits\n" +
                    "ScreenPad\t-\n" +
                    "Grafis\tNVIDIA GeForce RTX 2060\n" +
                    "Slot\tUSB Type-A;USB Type-C; AC Smart pin; Headphone/microphone; Mini DisplayPort; HDMI 2.0a\n" +
                    "Kamera\tHP Wide Vision HD Camera\n" +
                    "Koneksi\tIntel Wi-Fi 6 dan Bluetooth 5\n" +
                    "Audio\tBang & Olufsen\n" +
                    "Baterai\t6-cell, 70.9 Wh Li-ion polymer battery\n" +
                    "Dimensi\t14.09 in (W) x 9.44 in (D) x 0.89 in (H)",

            " Home\tInfo Komputer Update\tReview dan Harga Komputer Lenovo IdeaCentre C460\n" +
                    "Info Komputer Update\n" +
                    "Review dan Harga Komputer Lenovo IdeaCentre C460\n" +
                    "written by Douglas Chapman December 29, 2017\n" +
                    "\n" +
                    "Lenovo IdeaCentre C460 adalah komputer besutan Lenovo dengan tipe dekstop All – In – One PC. Apa itu All – In – One PC?. All-in-one PC adalah komputer yang komponen utamanya diletakkan dan dijadikan satu dengan casing monitornya. Tujuannya adalah agar lebih portable, lebih kecil, dan lebih praktis tanpa mengurangi fungsi utamanya sebagai komputer kerja.\n" +
                    "\n" +
                    "All-in-one PC biasanya selalu menggunakan monitor flat, bahkan di all-in-one PC terbaru rata-rata sudah menggunakan layar touchscreen. Komponen internal PC langsung diletakkan di belakang monitor tersebut secara ringkas.\n" +
                    "\n" +
                    "Bagi Anda yang sedang mencari komputer merek Lenovo, Lenovo dengan tipe ideaCentre C460 ini patut Anda pertimbangkan untuk menjadi pilihan teman kerja Anda di meja kantor. Perlu informasi mengenai spesifikasi komputer Lenovo IdeCentre C460? Berikut spesifikasinya.\n" +
                    "\n" +
                    "Spesifikasi Komputer Lenovo IdeaCentre C460\n" +
                    "\n" +
                    "Processor : Intel Core i7/Core i5/Core i3/Pentium i7-4770T i5- 4570T i3-4130T Pentium G3220T dengan kecepatan processor 2/5GHz/2.6GHz/2.9GHz\n" +
                    "\n" +
                    "VGA card : NVIDIA GeForce 705A\n" +
                    "\n" +
                    "RAM : 2GB/ 4GB/ 8GB tipe DDR 3\n" +
                    "\n" +
                    "Hard disk : 500GB/ 1TB/ 2TB tipe penyimpanan HDD\n" +
                    "\n" +
                    "Drive optical : DVD RAMBO reader / writer\n" +
                    "\n" +
                    "Ukuran Layar : 21.5 inch resolusi 1920×1080\n" +
                    "\n" +
                    "Fitur tambahan : HDMI, USB2.0, USB3.0, Bluetooth, Camera\n" +
                    "\n" +
                    "Sistem operasi : Windows 8/ Windows 8.1\n" +
                    "\n" +
                    "Kelebihan Komputer Lenovo IdeaCentre C460\n" +
                    "\n" +
                    "Untuk Anda yang membutuhkan mesin komputasi bertenaga namun enggan berurusan dengan desain yang besar dan terkesan ribet maka Lenovo IdeaCentre C460 adalah jawabannya. Desktop PC yang masuk dalam kategori AIO PC ini memiliki desain yang elegan dan juga praktis sehingga dapat ditaruh dimanapun yang kamu inginkan.",

            "Platform\tNotebook\n" +
                    "Tipe Prosesor\tIntel Core i5\n" +
                    "Processor Onboard\tIntel Core i5-1135G7 Processor (8M Cache, up to 4.20 GHz)\n" +
                    "Kapasitas Penyimpanan\t512 GB SSD\n" +
                    "Memori Standar\t8GB LPDDR4X\n" +
                    "Tipe Grafis\tIntel Iris Xe Graphics\n" +
                    "Ukuran Layar\t13.3 Inch\n" +
                    "Resolusi Layar\t2560 x 1600\n" +
                    "Tipe Layar\tAnti-Glare Display\n" +
                    "Wireless Network Type\tIntegrated\n" +
                    "Wireless Bluetooth\tIntegrated\n" +
                    "Keyboard\tBacklit keyboard\n" +
                    "Ragam Input Device\tTouchpad\n" +
                    "Audio\tIntegrated\n" +
                    "Speaker\tIntegrated\n" +
                    "Kamera\tIR & HD720p (1.0MP) camera with ToF sensor, fixed focus\n" +
                    "Sistem Operasi\tMicrosoft Windows 10 Home\n" +
                    "Software Lainnya\tMicrosoft Office Home Student 2019\n" +
                    "Daya / Power\t65W AC Adapter\n" +
                    "Dimensi (PTL)\t295.88 x 208.85 x 14.25 mm\n" +
                    "Berat Produk\t2.13 lbs\n" +
                    "Lain-lain\tDetail Garansi: 2Y Premium Care + 2Y ADP",

            "Touch Bar dan Touch ID\n" +
                    "    Layar Retina dengan lampu latar LED 13,3 inci (diagonal)\n" +
                    "    Prosesor Intel Core i5 dual‑core 2,9 GHz atau 3,1 GHz\n" +
                    "    Turbo Boost hingga 3,3 GHz\n" +
                    "    Kekuatan baterai hingga 10 jam1\n" +
                    "    SSD hingga 512 GB2\n" +
                    "    Trackpad Force Touch\n" +
                    "    1,37 kg (3,02 pound)3",

            "\n" +
                    "    Layar: 15.6 inches 1920×1080 piksel\n" +
                    "    Processor: Intel Core i7 7700HQ 2.5GHz up to 3.5GHz (6MB Cache)\n" +
                    "    Graphic Card: Nvidia GTX1050 4GB\n" +
                    "    RAM: 4 GB DDR4 2400Mhz\n" +
                    "    Storage: 128 GB SSD + 1 TB HDD\n" +
                    "    Konektivitas: 802.11 ac Wi-Fi + Bluetooth v4.2\n" +
                    "    Port: 1 SD card reader (XC/HC), 1 USB 3.0 Type-C, 2 USB 3.0 Type-A, 1 USB 2.0 Type-A, 1 HDMI, 1 Mini Display Port, 1 RJ-45, 1 microphone-in/headphone-out (SPDIF)k\n" +
                    "    Baterai: 6-cell baterai, 41 Whr\n"
    )
    private var computerImages = intArrayOf(
            R.drawable.acer_aspire_m564,
            R.drawable.acer_nitro,
            R.drawable.apple_imac_27,
            R.drawable.asuspro_d540mc,
            R.drawable.asuszephorus,
            R.drawable.hp_omen15,
            R.drawable.lenovo_ideacentre_c460,
            R.drawable.lenovo_yoga_slim7,
            R.drawable.macbookpro,
            R.drawable.msi
    )

    val listData: ArrayList<Computer>
        get() {
            val list = arrayListOf<Computer>()
            for (position in computerNames.indices){
                val computer = Computer()
                computer.name = computerNames[position]
                computer.detail = computerDetails[position]
                computer.photo = computerImages[position]
                list.add(computer)
            }
            return list
        }
}