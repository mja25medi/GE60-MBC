package egovframework.edutrack.comm.util.web;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import egovframework.edutrack.Constants;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	private static JedisPool pool = null;
	private static String connDate = "";
	
	
	static{
		borrow();
	}

	/**
	 * Get Value
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		Jedis jedis = borrow();
		String value = null;
        try {
        	value = jedis.get(key);
        	
        }
        catch (Exception e) { 
        	
        }
        finally {
        	if (jedis != null) {
        		jedis.close();
        		revert(jedis);
        	}
        }
        return value;
	}
	
	/**
	 * Set Value
	 * @param key
	 * @param value
	 * @param expireTime(second)
	 */
	public static void setValue(String key, String value, int expireTime) {
		Jedis jedis = borrow();
		
        try {
            jedis.set(key, value);

            if (expireTime > 0) { 
            	jedis.expire(key, expireTime);
            }
        } 
        catch (Exception e) { 
        	e.printStackTrace();
        }
        finally {
        	if (jedis != null) {
        		jedis.close();
        		revert(jedis);
        	}
        }
	}
	
	/**
	 * Set Value
	 * @param key
	 * @param value
	 */
	public static void setValue(String key, String value) {
		
		setValue(key, value, 0);
	}
	
	
	/**
	 * Get HashMap
	 * @param key
	 * @return
	 */
	public static Map getHmset(String key) {
		Jedis jedis = borrow();
		Map map = null;
        try {
        	map = jedis.hgetAll(key);
        	
        }
        catch (Exception e) { 
        	
        }
        finally {
        	if (jedis != null) {
        		jedis.close();
        		revert(jedis);
        	}
        }
        return map;
	}
	
	
	/**
	 * Set HashMap
	 * @param key
	 * @param map
	 * @param expireTime
	 */
	public static void setHmset(String key, Map map, int expireTime) {
		Jedis jedis = borrow();
		
        try {
            jedis.hmset(key, map);
            if (expireTime > 0) { 
            	jedis.expire(key, expireTime);
            }
        } 
        catch (Exception e) { 
        	e.printStackTrace();
        }
        finally {
        	if (jedis != null) {
        		jedis.close();
        		revert(jedis);
        	}
        }
	}
	
	/**
	 * Set HashMap
	 * @param key
	 * @param map
	 */
	public static void setHmset(String key, Map map) {
		
		setHmset(key, map, 0);
	}

	
	/**
	 * Get Keys
	 * @param pattern
	 * @return
	 */
	public static Set getKeys(String pattern) {
		Jedis jedis = borrow();
		Set keys = null;
        try {
        	keys = jedis.keys(pattern);
        	
        }
        catch (Exception e) { 
        	
        }
        finally {
        	if (jedis != null) {
        		jedis.close();
        		revert(jedis);
        	}
        }
        return keys;
	}

	
	/**
	 * Remove Keys
	 * @param pattern
	 */
	public static void removeKeys(String pattern) {
		Jedis jedis = borrow();
		
        try {
        	Set keys = jedis.keys(pattern);
        	
        	if (keys.size() > 0) {
	    		Iterator iterator = keys.iterator();
				String[] key = new String[keys.size()];
				int i = 0;
	    		while (iterator.hasNext()) {
	    			key[i++] = (String)iterator.next();
				}
	    		jedis.del(key);
    		}
        }
        catch (Exception e) { 
        	
        }
        finally {
        	if (jedis != null) {
        		jedis.close();
        		revert(jedis);
        	}
        }
	}
	
	
	
	/**
	 * Connect Server
	 */
	private static void connServer() {
		try {
            pool = new JedisPool(new JedisPoolConfig(), Constants.REDIS_SERVER, 
            		Integer.parseInt(Constants.REDIS_PORT),1000,Constants.REDIS_PASSWORD);
            connDate = getNowDate();
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}
	
	
	private static void destroy() {
		if (pool == null) {
			pool.destroy();
		}
    }
 
    private static Jedis borrow() {
    	Jedis jedis = null;
    	if (pool == null) {
    		connServer();
    	}
    	else {
    		String nowDate = getNowDate();
    		
    		if (!nowDate.equals(connDate)) {
    			destroy();
    			connServer();
    		}
    		try {
    			jedis = pool.getResource();
			} catch (Exception e) {
				destroy();
				pool = null;
				e.printStackTrace();
			}
    		
    	}
    	return jedis;
    }
 
    private static void revert(Jedis jedis) {
    	if (pool == null) {
    		pool.returnResource(jedis);
    	}
    }
    
    private static String getNowDate() {
    	Calendar calendar = Calendar.getInstance(Locale.getDefault());
    	String date = Integer.toString(calendar.get(Calendar.YEAR)) 
    			+ Integer.toString(calendar.get(Calendar.MONTH))
    			+ Integer.toString(calendar.get(Calendar.DAY_OF_MONTH))
    			+ Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
    	
    	return date;
    }
}
