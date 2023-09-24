import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key});

  @override
  Widget build(BuildContext context) {
    List<String> namax = [
      "Robi Bin Nur",
      "Robi Bin Nur",
      "Robi Bin Nur",
      "Robi Bin Nur",
      "Robi Bin Nur",
      "Robi Bin Nur",
      "Robi Bin Nur",
      "Robi Bin Nur",
      "Robi Bin Nur",
      "Robi Bin Nur",
      "Robi Bin Nur",
    ];
    String tes = "Section : BOH";
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      home: Scaffold(
        appBar: AppBar(
          leading: Icon(Icons.home),
          title: Text(
            'Praktek Uji Coba',
          ),
          centerTitle: true,
          actions: [
            Padding(
              padding: EdgeInsets.only(right: 16.0),
              child: Icon(
                Icons.menu,
              ),
            ),
          ],
        ),
        backgroundColor: const Color.fromARGB(255, 70, 111, 144),
        body: SafeArea(
          child: Padding(
            padding: const EdgeInsets.all(8.0),
            child: ListView.builder(
              itemCount: namax.length,
              itemBuilder: (context, index) {
                return ListTile(
                  leading: CircleAvatar(
                    backgroundImage: NetworkImage(
                      "https://www.tentangsinopsis.com/wp-content/uploads/2020/06/Pemain-Its-Okay-to-Not-Be-Okay-Kim-Soo-Hyun-pemeran-Moon-Kang-Tae.jpg",
                    ),
                  ),
                  title: Text(
                    namax[index],
                    style: TextStyle(
                      color: Colors.white, // Ubah warna sesuai keinginan Anda
                    ),
                  ),
                  subtitle: Text(
                    tes,
                    style: TextStyle(
                      color: Color.fromARGB(255, 160, 161, 160),
                    ),
                  ),
                );
              },
            ),
          ),
        ),
      ),
    );
  }
}
