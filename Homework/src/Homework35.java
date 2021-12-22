import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import components.map.Map.Pair;
import components.queue.Queue;
import components.queue.Queue1L;

public class Homework35 {

    /**
     * Raises the salary of all the employees in {@code map} whose name starts
     * with the given {@code initial} by the given {@code raisePercent}.
     *
     * @param map
     *            the name to salary map
     * @param initial
     *            the initial of names of employees to be given a raise
     * @param raisePercent
     *            the raise to be given as a percentage of the current salary
     * @updates map
     * @requires [the salaries in map are positive] and raisePercent > 0
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [the salaries of the employees in map whose names start with the given
     *  initial have been increased by raisePercent percent (and truncated to
     *  the nearest integer); all other employees have the same salary]
     * </pre>
     */
    private static void giveRaise(components.map.Map<String, Integer> map,
            char initial, int raisePercent) {
        Queue<String> names = new Queue1L<>();
        for (Pair<String, Integer> pair : map) {
            if (pair.key().charAt(0) == initial) {
                names.enqueue(pair.key());
            }
        }
        while (names.length() > 0) {
            String name = names.dequeue();
            int value = map.value(name);
            int temp = value * raisePercent;
            temp = temp / 100;
            value += temp;
            map.replaceValue(name, value);
        }
    }

    /**
     * Raises the salary of all the employees in {@code map} whose name starts
     * with the given {@code initial} by the given {@code raisePercent}.
     *
     * @param map
     *            the name to salary map
     * @param initial
     *            the initial of names of employees to be given a raise
     * @param raisePercent
     *            the raise to be given as a percentage of the current salary
     * @updates map
     * @requires <pre>
     * [the salaries in map are positive]  and  raisePercent > 0  and
     * [the dynamic types of map and of all objects reachable from map
     *  (including any objects returned by operations (such as entrySet() and,
     *  from there, iterator()), and so on, recursively) support all
     *  optional operations]
     * </pre>
     * @ensures <pre>
     * DOMAIN(map) = DOMAIN(#map)  and
     * [the salaries of the employees in map whose names start with the given
     *  initial have been increased by raisePercent percent (and truncated to
     *  the nearest integer); all other employees have the same salary]
     * </pre>
     */
    private static void giveRaise(java.util.Map<String, Integer> map,
            char initial, int raisePercent) {
        Deque<String> names = new ArrayDeque<String>();
        for (Map.Entry<String, Integer> pair : map.entrySet()) {
            if (pair.getKey().charAt(0) == initial) {
                names.push(pair.getKey());
            }
        }
        while (!names.isEmpty()) {
            String name = names.pop();
            int value = map.get(name);
            int temp = value * raisePercent;
            temp = temp / 100;
            value += temp;
            map.replace(name, value);
        }
    }

}