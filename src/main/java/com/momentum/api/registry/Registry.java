package com.momentum.api.registry;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Registry implementation backed by a {@link LinkedHashMap}. Registries are
 * a sub-implementation of {@link java.util.List} with {@link ILabeled}
 * elements to allow for faster get/search methods. The registry will throw a
 * {@link NullPointerException} if any elements in the map are provided with
 * a <tt>null</tt> label/data. NULL ELEMENTS ARE NOT SUPPORTED!
 *
 * <p>The following methods can now run in O(n): {@link #retrieve(String)},
 * {@link #retrieve(String, Class)} {@link #unregister(ILabeled)}</p>
 *
 * @author linus
 * @since 03/23/2023
 *
 * @param <T> The registry data type
 */
public class Registry<T extends ILabeled> implements IRegister<T>
{
    // register
    // underlying hash map with String keys
    protected Map<String, T> register = new LinkedHashMap<>();

    /**
     * Registers the given data to a register, which can later be retrieved
     * using {@link IRegister#retrieve(String)}
     *
     * @param l    The data label
     * @param data The data
     * @return The registered data
     * @throws NullPointerException if data is <tt>null</tt>
     */
    @Override
    public T register(String l, T data)
    {
        // null check
        if (data == null)
        {
            throw new NullPointerException(
                    "Null data not supported in registry");
        }

        // put in hashmap with param label
        return register.put(l, data);
    }

    /**
     * Registers the given data to a register, which can later be retrieved
     * using {@link IRegister#retrieve(String)}
     *
     * @param data The data
     * @return The registered data
     * @throws NullPointerException if data is <tt>null</tt>
     */
    @Override
    public T register(T data)
    {
        // null check
        if (data == null)
        {
            throw new NullPointerException(
                    "Null data not supported in registry");
        }

        // put in hashmap with data label
        register.put(data.getLabel(), data);
        return data;
    }

    /**
     * Unregisters the given data to a register, which removes its mapping from
     * the register. Unregistering data also frees its {@link ILabeled} label
     *
     * @param data The data
     * @throws NullPointerException if data is <tt>null</tt>
     */
    @Override
    public T unregister(T data)
    {
        // null check
        if (data == null)
        {
            throw new NullPointerException(
                    "Null data not supported in registry");
        }

        // remove from register
        return register.remove(data.getLabel());
    }

    /**
     * Retrieves the data from the register by the String value of the label
     *
     * @param l The label of the data
     * @return The data from the register
     * @throws NullPointerException if label is <tt>null</tt>
     */
    @Override
    public T retrieve(String l)
    {
        // null check
        if (l == null)
        {
            throw new NullPointerException(
                    "Null label not supported in registry");
        }

        // retrieve by label String
        return register.get(l);
    }

    /**
     * Retrieves the data from the register by the String value of the label,
     * Returns <tt>null</tt> if the retrieved data does not match the class type.
     *
     * @param l     The label of the data
     * @param clazz The class type of the data
     * @return The data from the register
     * @throws NullPointerException if label or the class type are <tt>null</tt>
     */
    @Override
    public T retrieve(String l, Class<?> clazz)
    {
        // null check
        if (l == null)
        {
            throw new NullPointerException(
                    "Null label not supported in registry");
        }

        // data class matches param class
        T data = register.get(l);
        if (clazz.isInstance(data))
        {
            return data;
        }

        // class type mismatch
        return null;
    }
}
