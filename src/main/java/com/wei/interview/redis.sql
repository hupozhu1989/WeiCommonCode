redis八大数据类型: String(字符类型)  Hash(散列类型)  List(列表类型)  Set(集合类型)  SortedSet(有序集合类型)
                  Bitmap(位图)  HyperLogLog(统计)  GEO(地理)
redis使用场景
    string
        商品编号、订单号采用INCR命令生成  incr items:001
        统计点赞数、文章阅读量  incr articles:001
    hash
        购物车
            新增商品 -> hset shopcar:uid1024 334477 1
            新增商品 -> hset shopcar:uid1024 334488 1
            增加商品数量 -> hincrby shopcar:uid1024 334477 1
            商品去重总数 -> hlen shopcar:uid1024
            全部选择 -> hgetall shopcar:uid1024
    list
        微信公众号
            ①大v作者李永乐和CSDN发布了文章分别是 11和22
            ②我关注了他们2个,只要他们发布文章,就会投递进我的list
            lpush likearticle:uid1024 11 22
            ③查看自己的号订阅的全部文章,类似分页,下面0~4就是一次显示5条
            lrange likearticle:uid1024 0 4
    set
        微信抽奖小程序
            ①用户ID,立即参与按钮    sadd lottery:reward001 uid1024
            ②显示已经有多少人参与了    scard lottery:reward001
            ③抽奖(从set中任意选取N个中奖人)
                srandmember lottery:reward001 2 随机抽奖2个人,元素不删除
                spop lottery:reward001 3 随机抽奖3个人,元素会删除
        微信朋友圈点赞
            ①新增点赞    sadd pub:msg10005 uid1024 uid1025 uid1026
            ②取消点赞    srem pub:msg10005 uid1024
            ③展现所有点赞过的用户 smembers pub:msg10005
            ④点赞用户数统计    scard pub:msg10005
            ⑤判断某个朋友是否对楼主点赞过 sismember pub:msg10005 uid1026
        微博好友关注社交关系
            sadd weibo:s1 1 2 3 4 5
            sadd weibo:s2 3 4 5 6 7
            共同关注的人
                sinter weibo:s1 weibo:s2
            可能认识的人
                s2可能认识  sdiff weibo:s1 weibo:s2
                s1可能认识  sdiff weibo:s2 weibo:s1
    zset
        根据商品销量对商品进行排序显示
            商品编号1001的销量是9,商品编号1002的销量是15    zadd goods:sellsort 9 item1001 15 item1002
            有个客户买了2件商品1001    zincrby goods:sellsort 2 item1001
            求商品销量前10名    zrange goods:sellsort 0 9
    bitmap
        setbit bittest 1 1
        setbit bittest 4 1
        setbit bittest 8 1
        getbit bittest 1
        getbit bittest 4
        bitcount bittest 0 -1
    HyperLogLog
        PFADD hypertest a b c d e f d g h a i j c
        PFCOUNT hypertest
    geo
        -- geoadd：添加地理位置的坐标，可以将一个或多个经度(longitude)、纬度(latitude)、位置名称(member)添加到指定的 key 中
        geoadd guangdong 113.23 23.16 guangzhou 114.07 22.62 shenzhen 116.63 23.68 chaozhou
        -- geodist：计算两个位置之间的距离
        geodist guangdong guangzhou shenzhen km
        -- georadius：根据用户给定的经纬度坐标来获取指定范围内的地理位置集合
        georadius guangdong 113 23 100 km
        georadius guangdong 113 23 200 km
        -- georadiusbymember：根据储存在位置集合里面的某个地点获取指定范围内的地理位置集合
        georadiusbymember guangdong guangzhou 200 km
        -- geopos：获取地理位置的坐标，用于从给定的 key 里返回所有指定名称(member)的位置（经度和纬度），不存在的返回 nil
        geopos guangdong guangzhou
        -- geohash：返回一个或多个位置对象的 geohash 值
        geohash guangdong guangzhou
