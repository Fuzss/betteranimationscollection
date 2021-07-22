package fuzs.betteranimationscollection.client.util;

import java.util.Iterator;

public class ModelUtil {

    public static <S> S getAtIndex(Iterator<S> iterator, int index) {

        for (int i = 0; i < index; i++) {

            iterator.next();
        }

        return iterator.next();
    }

}
