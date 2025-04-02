sfml3 android
---

## build sfml3 with android ndk

- Download SFML3 Source Code

```bash
# clone SFML v3.0.0
git clone https://github.com/SFML/SFML.git SFML
cd SFML
git checkout v3.0.0
```

- Download build script
```bash
curl -O -J https://gist.githubusercontent.com/iplanetcn/d6547ff80ef7d390fd0660d91d90c577/raw/build-sfml3-android.sh
chmod a+x build-sfml3-android-sh
```

- build (replace {ndk_path} with your own) and install
```bash
./build-sfml3-android-sh {ndk_path}
cd arm64-v8a
make install
```

- Download this repo and open in Android Studio latest version, build and run. Enjoy!!!
> (tested on Android Studio Meerkat | 2024.3.1 Patch 1) 