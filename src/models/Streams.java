package models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

// В общем сделать так, чтобы все функции за одну итерацию по массиву выполнялись
public class Streams<T> {

    /** Main collection */
    private static List streamList;

    /** List of filters
     * Adding new filter per calling .filter()
     * All filters using only after calling .toMap()
     * */
    private static List<Predicate> filtersList;

    /** List of functions
     * Adding new function per calling .transform()
     * All functions using only after calling .toMap()
     * */
    private static List<Function> functionsList;

    public static List getStreamList() {
        return streamList;
    }

    public static Streams of(List list){
        streamList = new LinkedList(list);
        functionsList = new LinkedList<>();
        filtersList = new LinkedList<>();
        return new Streams();
    }

    public Streams<T> filter(Predicate<? super T> predicate){

        filtersList.add(predicate);
        return this;
    }

    public Streams<? super T> transform(Function<? super T, ? super T> function){

        functionsList.add(function);
        return this;
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyFunction,
                           Function<? super T, ? extends V> valueFunction){

        Map<K, V> map = new HashMap();

        boolean addToResult;
        Object transformedObject;

        for (Object obj: streamList){

            addToResult = true;

            for (Predicate filter: filtersList){
                if (!filter.test(obj)) {
                    addToResult = false;
                    break;
                }
            }

            if (!addToResult) continue;

            transformedObject = obj;
            for (Function function: functionsList){
                System.out.println("Here");
                System.out.println(obj);
                transformedObject = function.apply((T)transformedObject);
            }
            map.put(keyFunction.apply((T)transformedObject),
                    valueFunction.apply((T)transformedObject));
        }
        return map;
    }

}
