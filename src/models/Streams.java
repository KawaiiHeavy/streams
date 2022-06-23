package models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class Streams<T> {

    private static List streamList;

    public static List getStreamList() {
        return streamList;
    }

    public static Streams of(List list){
        streamList = new LinkedList(list);
        return new Streams();
    }

    public Streams<T> filter(Predicate<? super T> predicate){
        List result = new LinkedList();

        streamList.forEach(o -> {
            if (predicate.test((T)o)) result.add(o);
        });

        streamList = result;
        return this;
    }

    public Streams<? super T> transform(Function<? super T, ? super T> function){

        List result = new LinkedList();

        streamList.forEach(o -> {
            result.add(function.apply((T)o));
        });

        streamList = result;
        return this;
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyFunction,
                           Function<? super T, ? extends V> valueFunction){
        Map<K, V> map = new HashMap();

        streamList.forEach(o -> {
            map.put(keyFunction.apply((T)o),
                    valueFunction.apply((T)o));
        });

        return map;
    }

}
