package net.risenteam.risencore;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.risenteam.risencore.adapters.ItemStackAdapter;
import net.risenteam.risencore.utils.Logger;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class RisenPlugin extends JavaPlugin {

    private final Gson gson = createGson();
    private long enablingSystem;

    private Gson createGson(){
        ItemStackAdapter adapter = new ItemStackAdapter();
        Gson buildingGson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<ItemStack>(){}.getType(), adapter)
                .create();

        adapter.setGson(buildingGson);
        return buildingGson;
    }

    public Gson getGson() {
        return gson;
    }

    /**
     * @deprecated Please use {@link RisenPlugin#onLoading()} which is call in this method
     */
    @Override
    @Deprecated
    public void onLoad() {
        enablingSystem = System.currentTimeMillis();
        this.onLoading();
    }

    /**
     * @deprecated Please use {@link RisenPlugin#onEnabling()} which is call in this method
     */
    @Override
    @Deprecated
    public void onEnable() {
        this.onEnabling();
        Logger.log("Successfully enabled in " + (System.currentTimeMillis() - enablingSystem) + "ms");
    }

    public void onLoading(){}
    public void onEnabling(){}
}
