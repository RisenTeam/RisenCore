package net.risenteam.risencore.commands;

import com.google.common.collect.ImmutableList;
import org.bukkit.util.StringUtil;

import java.util.*;

public class Parameters {

    public final int position;
    private String previousArg;
    public final Map<String, String> values;

    public Parameters(int position, String ... values) {
        this.position = position;
        this.values = new HashMap<>();
        for (String value : values) {
            this.values.put(value, "");
        }
    }

    public Parameters(int position) {
        this.position = position;
        this.values = new HashMap<>();
    }

    public void addValue(String name, String description){
        this.values.put(name, description);
    }

    public void setPreviousArg(String previousArg) {
        this.previousArg = previousArg;
    }

    public String getPreviousArg() {
        return previousArg;
    }

    public int getPosition() {
        return position;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public List<String> tabComplete(String[] args){
        if (args.length == 0) {
            return ImmutableList.of();
        } else {
            if(args.length >= 2){
                String arg = args[args.length - 2];
                if(getPreviousArg() != null && !arg.equalsIgnoreCase(getPreviousArg())){
                    return new ArrayList<>();
                }
            }
            String lastWord = args[args.length - 1];
            ArrayList<String> matchedCompleter = new ArrayList<>();
            String[] argList = this.values.keySet().toArray(new String[0]);

            for (String arg : argList) {
                if (StringUtil.startsWithIgnoreCase(arg, lastWord)) {
                    matchedCompleter.add(arg);
                }
            }

            matchedCompleter.sort(String.CASE_INSENSITIVE_ORDER);
            return matchedCompleter;
        }
    }
}
