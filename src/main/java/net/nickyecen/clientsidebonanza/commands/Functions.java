package net.nickyecen.clientsidebonanza.commands;

import com.mojang.brigadier.context.CommandContext;

import net.nickyecen.clientsidebonanza.calculator.Evaluator;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

import static net.minecraft.text.Text.literal;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.DoubleArgumentType.getDouble;
import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static net.nickyecen.clientsidebonanza.commands.Variable.getVar;

public class Functions {
	
	public static int calc(CommandContext<FabricClientCommandSource> ctx) {
		
		ctx.getSource().sendFeedback(literal(Evaluator.evaluate(getString(ctx, "expression"))));
		
		return 1;
		
	}
	
	public static int printVar(CommandContext<FabricClientCommandSource> ctx) {
		
		String functionName = getString(ctx, "name");		
		Variable var = getVar(functionName);
		
		if(var == null) {
			
			ctx.getSource().sendError(literal("Variable not found."));
			
			return 0;
		}
		
		ctx.getSource().sendFeedback(literal(var.toString()));
		
		return 1;
		
	}
	
	public static int printAllVar(CommandContext<FabricClientCommandSource> ctx) {
		
		for(Variable var : Variable.vars) {
			
			ctx.getSource().sendFeedback(literal(var.name + ": " + var.toString()));
			
		}
		
		return 1;
		
	}
	
	public static int copy(CommandContext<FabricClientCommandSource> ctx) {
		
		ctx.getSource().getClient().keyboard.setClipboard(getString(ctx, "value"));
		
		return 1;
		
	}
	
	public static int catchFire(CommandContext<FabricClientCommandSource> ctx) {
		
		ctx.getSource().getClient().player.setOnFire(true);
		
		return 1;
		
	}

	public static int setSensitivity(CommandContext<FabricClientCommandSource> ctx) {
		
		ctx.getSource().getClient().options.getMouseSensitivity().setValue(getDouble(ctx, "value"));
		
		return 1;
		
	}
	
	public static int getTime(CommandContext<FabricClientCommandSource> ctx) {
		
		ctx.getSource().sendFeedback(literal(""+ctx.getSource().getWorld().getTimeOfDay()));
		
		return 1;
		
	}
	
	public static int getCoord(CommandContext<FabricClientCommandSource> ctx) {
		
		String x = "X: " + ctx.getSource().getPlayer().getX();
		String y = "Y: " + ctx.getSource().getPlayer().getY();
		String z = "Z: " + ctx.getSource().getPlayer().getZ();
		
		ctx.getSource().sendFeedback(literal(x + ", "+ y + ", " + z));
		
		return 1;
		
	}
	
	public static int kickPassengers(CommandContext<FabricClientCommandSource> ctx) {
		
		ctx.getSource().getPlayer().removeAllPassengers();
		
		return 1;
		
	}
	
	public static int resizeWindow(CommandContext<FabricClientCommandSource> ctx) {
		
		ctx.getSource().getClient().getWindow().setWindowedSize(getInteger(ctx, "x"), getInteger(ctx, "y"));
		
		return 1;
		
	}
	
	public static int disconnect(CommandContext<FabricClientCommandSource> ctx) {
		
		ctx.getSource().getClient().disconnect();
		
		return 1;
		
	}
	
}
