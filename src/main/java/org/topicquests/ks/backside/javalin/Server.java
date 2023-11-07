/**
 * 
 */
package org.topicquests.ks.backside.javalin;

import java.util.Map;

import org.topicquests.support.RootEnvironment;
import org.topicquests.support.config.Configurator;
import org.topicquests.support.util.LoggingPlatform;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import org.tinylog.Logger;
/**
 * @author jackpark
 *
 */
public abstract class Server {
	private Map<String,Object>properties;
	protected Javalin app = null;
	
	
	/**
	 * 
	 */
	public Server() {
		properties = Configurator.getProperties("javalin-server-props.xml");
		String p = (String)properties.get("ServerPort");
		int port = Integer.parseInt(p);
		app = Javalin.create(config -> {
			config.enableCorsForAllOrigins();
            config.addStaticFiles("/public");
            config.registerPlugin(new RouteOverviewPlugin("/routes"));
        }).start(port);
		//setRoutes(app);
	}
	
	/**
	 * <p>Extending class must declare routes and build the apps to support them</p>
	 * <p>Extending class can declare a {@code main()} method</p.
	 * @param app
	 */
	public abstract void setRoutes(Javalin app);


}
