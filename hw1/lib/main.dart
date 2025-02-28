import 'dart:io';

import 'package:camera/camera.dart';
import 'package:flutter/material.dart';

Future<void> main() async {
  WidgetsFlutterBinding.ensureInitialized();
  final cameras = await availableCameras(); 
  final firstCamera = cameras.first; 
  runApp(MyApp(camera: firstCamera));
}

class MyApp extends StatelessWidget {
  const MyApp({super.key, required this.camera});
  final CameraDescription camera;

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: CameraScreen(camera: camera),
    ); 
  }
}

class CameraScreen extends StatefulWidget {
  final CameraDescription camera;

  const CameraScreen({Key? key, required this.camera}) : super(key: key);

  @override
  _CameraScreenState createState() => _CameraScreenState();
}

class _CameraScreenState extends State<CameraScreen> {
  late CameraController _cameraController;
  late Future<void> _initializeControllerFuture;
  String? _imagePath;

  @override
  void initState() {
    super.initState();
    _initializeCamera();
  }
  Future<void> _initializeCamera() async {
    // Get the list of available cameras
    try{
      final cameras = await availableCameras();

    // Use the first camera in the list (usually the back camera)
    final firstCamera = cameras.first;

    // Initialize the CameraController
    _cameraController = CameraController(
      firstCamera,
      ResolutionPreset.medium,
    );
    
    if (mounted){
      setState(() {});
    }

    // Initialize the controller and store the Future
    _initializeControllerFuture = _cameraController.initialize();
    } catch (e){
      print(e);
    }
  }

  @override
  void dispose() {
    _cameraController.dispose();
    super.dispose();
  }

  Future<void> _takePicture() async {
    try {
      await _initializeControllerFuture; 
      final image = await _cameraController.takePicture(); 
      setState(() {
        _imagePath = image.path; 
      });
    } catch (e) {
      print(e);
    }
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Камера')),
      body: Column(
        children: [
          Expanded(
            child: _imagePath == null
                ? FutureBuilder<void>(
                    future: _initializeControllerFuture,
                    builder: (context, snapshot) {
                      if (snapshot.connectionState == ConnectionState.done) {
                        return CameraPreview(_cameraController); 
                      } else {
                        return Center(child: CircularProgressIndicator());
                      }
                    },
                  )
                : Image.file(File(_imagePath!)), 
          ),
          FloatingActionButton(
// Provide an onPressed callback.
            onPressed: _takePicture,
          child: const Icon(Icons.camera_alt),
        )
        ],
      ),
    );
  }
}
