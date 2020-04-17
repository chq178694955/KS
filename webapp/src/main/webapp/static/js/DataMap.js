/*
 * MAP对象，实现MAP功能
 *
 * 接口：
 * size()     获取MAP元素个数
 * isEmpty()    判断MAP是否为空
 * clear()     删除MAP所有元素
 * put(key, value)   向MAP中增加元素（key, value)
 * remove(key)    删除指定KEY的元素，成功返回True，失败返回False
 * get(key)    获取指定KEY的元素值VALUE，失败返回NULL
 * element(index)   获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
 * containsKey(key)  判断MAP中是否含有指定KEY的元素
 * containsValue(value) 判断MAP中是否含有指定VALUE的元素
 * values()    获取MAP中所有VALUE的数组（ARRAY）
 * keys()     获取MAP中所有KEY的数组（ARRAY）
 *
 * 例子：
 * var map = new Map();
 *
 * map.put("key", "value");
 * var val = map.get("key")
 * ……
 *
 */
function DataMap(){
    this.elements = [];

    this.size = function(){
        return this.elements.length;
    }

    this.isEmpty = function(){
        return (this.elements.length < 1);
    }

    this.clear = function(){
        this.elements = [];
    }

    this.put = function(_key,_value){
        this.elements.push({
            key: _key,
            value: _value
        })
    }

    this.removeByKey = function(_key){
        var flag = false;
        try{
            for(i=0;i<this.elements.length;i++){
                if(this.elements[i].key == _key){
                    this.elements.splice(i,1);
                    return true;
                }
            }
        }catch (e) {
            console.log(e);
            flag = false;
        }
        return flag;
    }

    this.removeByValue = function(_value){
        var flag = false;
        try{
            for(i=0;i<this.elements.length;i++){
                if(this.elements[i].value == _value){
                    this.elements.splice(i,1);
                    return true;
                }
            }
        }catch(e){
            console.log(e);
            flag = false;
        }
        return flag;
    }

    this.get = function(_key){
        try{
            for(i=0;i<this.elements.length;i++){
                if(this.elements[i].key == _key){
                    return this.elements[i].value;
                }
            }
        }catch(e){
            console.log(e);
            return null;
        }
        return null;
    }

    this.index = function(_index){
        if(_index < 0 || _index > this.elements.length){
            return null;
        }
        return this.elements[_index];
    }

    this.containsKey = function(_key){
        try{
            for(var i=0;i<this.elements.length;i++){
                if(this.elements[i].key == _key){
                    return true;
                }
            }
        }catch (e) {
            console.log(e);
            return false;
        }
        return false;
    }

    this.containsValue = function(_value){
        try {
            for(i=0;i<this.elements.length;i++){
                if(this.elements[i].value == _value){
                    return true;
                }
            }
        }catch (e) {
            console.log(e);
            return false;
        }
        return false;
    }

    this.keys = function(){
        var keys = [];
        for(i=0;i<this.elements.length;i++){
            keys.push(this.elements[i].key);
        }
        return keys;
    }
}