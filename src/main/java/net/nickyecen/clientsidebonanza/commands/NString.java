package net.nickyecen.clientsidebonanza.commands;

import java.util.LinkedList;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;

public class NString extends Variable {

	String value;
	public static LinkedList<NString> strings = new LinkedList<NString>();
	
	public NString(String name, String value) {
		
		this.name = name;
		this.value = value;
		
		strings.add(this);
		vars.add(this);
		
	}
	
	@Override
	public String toString() {
		
		return value;
		
	}

	public static int newString(CommandContext<FabricClientCommandSource> ctx) {
		
		new NString(getString(ctx, "name"), getString(ctx, "value"));
		
		return 1;
		
	}
	
}
