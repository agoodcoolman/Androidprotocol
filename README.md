# Androidprotocol
***

["服务端代码"](https://github.com/agoodcoolman/Auth2)

1.此项目中使用的相关资源均上传在项目下的 protoc.exe  .便于下载使用.


2.每个项目应用protocol协议的时候,需要引用的jar包在项目的libs目录下,是自己编译的.不记得版本了.

3.[protocol协议的编写的指导 英文版](https://developers.google.com/protocol-buffers/docs/proto3)

4.生成对应的java文件



    上面的是根据proto文件生成java文件.

	protoc –java_out=../ –proto_path= .\addressbook.proto

	1,其中–java_out=../ 表示输出java在当前的目录下 

	2.表示–proto_path= .\addressbook.proto proto的目录 ./代表当前的目录.



5.assets 文件夹下的  addressbook.proto 是个demo


6.最后生成的使用的指令 ,给自己以后参考
>E:\workspace\3\Protocoltest\assets>protoc -I=E:\workspace\3\Protocoltest\assets
--java_out=../ E:\workspace\3\Protocoltest\assets\person.proto

E:\workspace\3\Protocoltest\assets>protoc -I=E:\workspace\3\Protocoltest\assets
--java_out=../src E:\workspace\3\Protocoltest\assets\result.proto


[参考](http://blog.csdn.net/agoodcoolman/article/details/51519799)
