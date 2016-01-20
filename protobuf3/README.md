# 生成java文件
cd ./rpc/protobuf/src/main/proto
protoc --java_out=./ user.proto
