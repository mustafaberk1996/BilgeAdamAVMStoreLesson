package com.example.avmstorelesson

import com.example.avmstorelesson.data.model.Photo
import com.example.avmstorelesson.data.model.Store
import com.example.avmstorelesson.data.model.User

object Database {

    val users = listOf(
        User(1, "mustafa","123"),
        User(1, "mustafa1","123"),
        User(1, "mustafa2","123")
    )

    val stores = mutableListOf(
        Store(1,"Adidas","https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Adidas_Logo.svg/2560px-Adidas_Logo.svg.png", "1","A","Adidas, dünyanın en büyük spor eşyası üreticilerinden birisidir. Adını kurucusu Adolf (Adi) Dassler'den almıştır."),
        Store(2,"Nike","https://pngimg.com/uploads/nike/small/nike_PNG18.png", "1","A","Amerika Birleşik Devletleri merkezli, önde gelen spor ayakkabı, spor giysi ve spor aksesuarları tasarımcısı ve pazarlayıcısı şirket ve bu ürünlerde kullanılan markadır."),
        Store(3,"Starbucks","https://pngimg.com/uploads/starbucks/small/starbucks_PNG6.png", "2","B","Amerikalı kahve dükkânları zinciridir. Merkezi Seattle, Washington'dadır. Starbucks, adını Moby Dick'teki Starbuck adlı karakterden alır ve simgesi bir denizkızıdır. "),
        Store(4,"Burger King","https://pngimg.com/uploads/burger_king/small/burger_king_PNG6.png", "2","B","Burger King, 1954 yılında 'McLamoreDavid' tarafından kurulan Amerikan fast-food lokantalar zinciridir. İlk Burger King restoranı 4 Aralık 1954'te Miami'de açıldı. Sloganı Have It Your Way (İstediğiniz Gibi)'dir."),
        Store(5,"Apple","https://yt3.googleusercontent.com/ytc/AOPolaQDCY5snh7OevgXGOTQYLWCPWgeReNQzUSSKTlPbQ=s900-c-k-c0x00ffffff-no-rj", "2","B","Apple Inc. ya da eski adıyla Apple Computer Inc., merkezi Cupertino'da[5] bulunan; tüketici elektroniği, bilgisayar yazılımı ve kişisel bilgisayar tasarlayan, geliştiren ve satan Amerikan çok uluslu şirket"),
        Store(5,"KayseriOguzSpor","https://upload.wikimedia.org/wikipedia/tr/thumb/c/c2/Kayserispor_logosu.png/640px-Kayserispor_logosu.png", "2","B","Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."),
    )


    val photos = mutableListOf(
        Photo("https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Adidas_Logo.svg/2560px-Adidas_Logo.svg.png"),
        Photo("https://upload.wikimedia.org/wikipedia/commons/0/08/Kartal_Yuvas%C4%B1_Logosu.png"),
        Photo("https://media.fenerbahce.org/getmedia/9adaeb53-1412-4bc3-b3c3-acc1c4b5be19/feneriumbandirma1200.jpg?width=1200&height=675&ext=.jpg"),
        Photo("https://upload.wikimedia.org/wikipedia/tr/thumb/c/c2/Kayserispor_logosu.png/640px-Kayserispor_logosu.png"),
        Photo("https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/Burger_King_logo_%281999%29.svg/2024px-Burger_King_logo_%281999%29.svg.png"),
        Photo("https://upload.wikimedia.org/wikipedia/commons/thumb/4/4b/McDonald%27s_logo.svg/2560px-McDonald%27s_logo.svg.png")
    )
}