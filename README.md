sfml3 android
---

## build sfml3 with android ndk
- Download SFML3 Source Code
```bash
# clone SFML v3.0.0
git clone https://github.com/SFML/SFML.git SFML
cd SFML
git checkout v3.0.0

# download build script
curl -O -J https://gist.githubusercontent.com/iplanetcn/d6547ff80ef7d390fd0660d91d90c577/raw/build-sfml3-android.sh
chmod a+x build-sfml3-android-sh

# build (replace {ndk_path} with your own)
./build-sfml3-android-sh {ndk_path}

# install
cd arm64-v8a
make install
```

