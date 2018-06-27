# docking1688
***
**多用户授权下调用1688接口API授权流程**

以下是官方给出的授权的流程图：
![1688](https://github.com/Foutlook/Foutlook.github.io/blob/master/2018/06/19/1688%E5%AF%B9%E6%8E%A5/%E6%8E%88%E6%9D%83%E6%B5%81%E7%A8%8B.jpg "授权流程")

**第一步：
发送一个授权请求，通过向1688发起授权会让用户输入用户名密码，并确认授权。输入正确后会可以得到一个临时授权码code，并跳转到拼接在授权请求路径上的回调路径。**

**第二步：
向回调地址请求中传入code，再传入appKey和secKey便可以得到access_token。**

如果想完全的了解整个的授权过程，可以官方文档或者 [我的博客](http://fxkoutlook.cn "xingkai.fan")。
