package com.dicoding.estomihi.mymovies.utils

import com.dicoding.estomihi.mymovies.R
import com.dicoding.estomihi.mymovies.model.MovieEntity

object DataDummy {
    fun generateDummyMovies(): List<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "M_1",
                "A Star Is Born",
                R.drawable.poster_a_start_is_born,
                "Seorang bintang musik country yang karirnya mulai memudar, Jackson Maine (Bradley Cooper) menemukan sebuah talenta yang sangat berbakat di dalam diri dari seorang musisi muda bernama Ally (Lady Gaga). Maine berhasil mengorbitkan Ally menjadi seorang bintang muda yang menjanjikan. Namun keduanya terlibat hubungan yang lebih jauh dari sekedar mentor dan anak didik. Seiring dengan meroketnya karir dari Ally dan dirinya, Maine mengalami dilema mengenai masalah ini.",
                "Bradley Cooper",
                3.5,
                "Drama, Percintaan, Musik")
        )
        movies.add(
            MovieEntity(
                "M_2",
                "Alita: Battle Angle",
                R.drawable.poster_alita,
                "Ketika Alita terbangun tanpa ingatan tentang siapa dia di dunia masa depan yang tidak dia kenal, dia ditangkap oleh Ido, seorang dokter yang penuh kasih yang menyadari bahwa di suatu tempat dalam cangkang cyborg yang ditinggalkan ini adalah hati dan jiwa seorang wanita muda dengan luar biasa. lalu.",
                "Robert Rodriguez",
                3.2,
                "Aksi, Cerita Fiksi, Petualangan")
        )
        movies.add(
            MovieEntity(
                "M_3",
                "Bohemian Rhapsody",
                R.drawable.poster_bohemian,
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Anthony McCarten",
                4.0,
                "Musik, Drama, Sejarah")
        )
        movies.add(
            MovieEntity(
                "M_4",
                "Cold Pursuit",
                R.drawable.poster_cold_persuit,
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "Hans Petter Moland",
                2.7,
                "Aksi, Kejahatan, Cerita Seru")
        )
        movies.add(
            MovieEntity(
                "M_5",
                "How to Train Your Dragon: The Hidden World",
                R.drawable.poster_how_to_train,
                "Ketika Hiccup memenuhi mimpinya untuk menciptakan utopia naga yang damai, penemuan Toothless 'dari pasangan yang tak teruji dan sukar ditangkap membuat Night Fury menjauh. Ketika bahaya meningkat di rumah dan pemerintahan Hiccup sebagai kepala desa diuji, baik naga dan pengendara harus membuat keputusan yang mustahil untuk menyelamatkan jenis mereka.",
                "Dean DeBlois",
                3.8,
                "Fantasi, Petualangan, Animasi, Keluarga")
        )
        movies.add(
            MovieEntity(
                "M_6",
                "Avengers: Infinity War",
                R.drawable.poster_infinity_war,
                "Karena Avengers dan sekutunya terus melindungi dunia dari ancaman yang terlalu besar untuk ditangani oleh seorang pahlawan, bahaya baru telah muncul dari bayangan kosmik: Thanos. Seorang lalim penghujatan intergalaksi.",
                "Anthony Russo",
                4.3,
                "Petualangan, Aksi, Cerita Fiksi")
        )
        movies.add(
            MovieEntity(
                "M_7",
                "Ralph Breaks the Internet",
                R.drawable.poster_ralph,
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                "Phil Johnston",
                3.8,
                "Keluarga, Animasi, Komedi, Petualangan")
        )
        movies.add(
            MovieEntity(
                "M_8",
                "Robin Hood",
                R.drawable.poster_robin_hood,
                "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                "Ben Chandler",
                3.0,
                "Petualangan, Aksi, Cerita Seru")
        )
        movies.add(
            MovieEntity(
                "M_9",
                "Spider-Main: Into the Spider-Verse",
                R.drawable.poster_spiderman,
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "Rodney Rothman",
                4.4,
                "Aksi, Petualangan, Animasi, Cerita Fiksi, Komedi")
        )
        movies.add(
            MovieEntity(
                "M_10",
                "T-34",
                R.drawable.poster_t34,
                "In 1944, a courageous group of Russian soldiers managed to escape from German captivity in a half-destroyed legendary T-34 tank. Those were the times of unforgettable bravery, fierce fighting, unbreakable love, and legendary miracles.",
                "Alexey Sidorov",
                3.3,
                "Kejahatan, Aksi, Drama, Sejarah")
        )

        return movies
    }

    fun generateDummyTvShows(): List<MovieEntity> {
        val tvShows = ArrayList<MovieEntity>()

        tvShows.add(
            MovieEntity(
                "TV_1",
                "The Arrow",
                R.drawable.poster_arrow,
                "Panah adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow",
                "Don McBrearty",
                3.0,
                "Kejahatan, Drama, Misteri", )
        )
        tvShows.add(
            MovieEntity(
                "TV_2",
                "The Simpsons",
                R.drawable.poster_the_simpson,
                "Bertempat di Springfield, kota rata-rata di Amerika, pertunjukan ini berfokus pada kejenakaan dan petualangan sehari-hari keluarga Simpson; Homer, Marge, Bart, Lisa dan Maggie, serta ribuan pemain virtual. Sejak awal, serial ini telah menjadi ikon budaya pop, menarik ratusan selebriti menjadi bintang tamu. Acara ini juga menjadi terkenal karena satirnya yang tak kenal takut terhadap kehidupan politik, media, dan Amerika secara umum.",
                "Matt Groening",
                3.8,
                "Keluarga, Animasi, Komedi")
        )
        tvShows.add(
            MovieEntity(
                "TV_3",
                "Doom Patrol",
                R.drawable.poster_doom_patrol,
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "Jeremy Carver",
                3.6,
                "Sci-fi & Fantasy, Komedi, Drama")
        )
        tvShows.add(
            MovieEntity(
                "TV_4",
                "NCIS",
                R.drawable.poster_ncis,
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "Magel Gultom",
                3.6,
                "Aksi & Petualangan, Kejahatan, Drama")
        )
        tvShows.add(
            MovieEntity(
                "TV_5",
                "Family Guy",
                R.drawable.poster_family_guy,
                "Seri animasi animasi Freakin 'Sweet yang sakit, terpelintir, dan salah, menampilkan petualangan keluarga Griffin yang disfungsional. Peter yang kikuk dan Lois yang sudah lama menderita memiliki tiga anak. Stewie (bayi yang brilian tetapi sadis yang bertekad membunuh ibunya dan mengambil alih dunia), Meg (yang tertua, dan merupakan gadis yang paling tidak populer di kota) dan Chris (anak tengah, dia tidak terlalu cerdas tetapi memiliki hasrat untuk film ).",
                "Hamonangan Sitorus",
                3.2,
                "Animasi, Komedi",)
        )
        tvShows.add(
            MovieEntity(
                "TV_6",
                "Naruto Shippuden",
                R.drawable.poster_naruto_shipudden,
                "Naruto Shippuuden adalah kelanjutan dari serial TV animasi asli Naruto. Kisah ini berkisah tentang Uzumaki Naruto yang lebih tua dan sedikit lebih matang dan upayanya untuk menyelamatkan temannya Uchiha Sasuke dari cengkeraman Shinobi seperti ular, Orochimaru. Setelah 2 setengah tahun, Naruto akhirnya kembali ke desanya Konoha, dan mulai mewujudkan ambisinya, meskipun itu tidak akan mudah, karena Ia telah mengumpulkan beberapa musuh (lebih berbahaya), seperti organisasi shinobi. ; Akatsuki.",
                "Grady Kishomoto",
                4.2,
                "Animasi, Komedi, Drama")
        )
        tvShows.add(
            MovieEntity(
                "TV_7",
                "The Flash",
                R.drawable.poster_flash,
                "Setelah akselerator partikel menyebabkan badai aneh, Penyelidik CSI Barry Allen disambar petir dan jatuh koma. Beberapa bulan kemudian dia terbangun dengan kekuatan kecepatan super, memberinya kemampuan untuk bergerak melalui Central City seperti malaikat penjaga yang tak terlihat. Untuk saat ini, hanya beberapa teman dekat dan rekan yang tahu bahwa Barry secara harfiah adalah manusia tercepat, tetapi tidak lama sebelum dunia mengetahui apa yang menjadi Barry Allen ... The Flash.",
                "Alek Simbolon",
                3.8,
                "Drama, Sci-fi & Fantasy")
        )
        tvShows.add(
            MovieEntity(
                "TV_8",
                "Hanna",
                R.drawable.poster_hanna,
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "David Farr",
                3.5,
                "Aksi & Petualangan, Drama")
        )
        tvShows.add(
            MovieEntity(
                "TV_9",
                "Gotham",
                R.drawable.poster_gotham,
                "Semua orang tahu nama Komisaris Gordon. Dia adalah salah satu musuh terbesar dunia kejahatan, seorang pria yang reputasinya identik dengan hukum dan ketertiban. Tapi apa yang diketahui tentang kisah Gordon dan kenaikannya dari detektif pemula ke Komisaris Polisi? Apa yang diperlukan untuk menavigasi berbagai lapisan korupsi yang diam-diam memerintah Kota Gotham, tempat bertelurnya penjahat paling ikonik di dunia? Dan keadaan apa yang menciptakan mereka.",
                "Bruno Heller",
                3.5,
                "Drama, Kejahatan, Sci-fi & Fantasy")
        )
        tvShows.add(
            MovieEntity(
                "TV_10",
                "Grey's Anatomy",
                R.drawable.poster_grey_anatomy,
                "Ikuti kehidupan pribadi dan profesional sekelompok dokter di Rumah Sakit Gray Sloan Memorial di Seattle.",
                "Abednego Parker",
                4.0,
                "Drama")
        )
        return tvShows
    }
}