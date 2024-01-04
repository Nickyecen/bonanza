package net.nickyecen.clientsidebonanza.commands;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static com.mojang.brigadier.arguments.StringArgumentType.greedyString;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static com.mojang.brigadier.arguments.DoubleArgumentType.doubleArg;

import static net.nickyecen.clientsidebonanza.commands.NString.newString;
import static net.nickyecen.clientsidebonanza.commands.NDouble.newDouble;
import static net.nickyecen.clientsidebonanza.commands.NInteger.newInteger;
import static net.nickyecen.clientsidebonanza.commands.Functions.*;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

public class NCommand {
	
	public static void register() {
		
		ClientCommandRegistrationCallback.EVENT.register((disp, access) -> {
			// Registro do Comando /n
			disp.register(literal("n")
					
					// Registro do subcomando var
					.then(literal("var")
							// Pega nome da variável
							.then(argument("name", string())
									// Pega valor inteiro
									.then(argument("integer", integer()).executes(ctx -> newInteger(ctx)))
									// Pega valor double
									.then(argument("double", doubleArg()).executes(ctx -> newDouble(ctx)))
									// Pega string
									.then(literal("string")
											.then(argument("value", greedyString()).executes(ctx -> newString(ctx))))
									))
					
					// Registro do subcomando fun
					.then(literal("fun")
							// Função printValue
							.then(literal("printVar")
									.then(argument("name", string()).executes(ctx -> printVar(ctx))))
							// Função printAllVar
							.then(literal("printAllVar").executes(ctx -> printAllVar(ctx)))
							.then(literal("copy")
									.then(argument("value", string()).executes(ctx -> copy(ctx))))
							.then(literal("catchFire").executes(ctx -> catchFire(ctx)))
							.then(literal("setSensitivity")
									.then(argument("value", doubleArg()).executes(ctx -> setSensitivity(ctx))))
							.then(literal("getTime").executes(ctx -> getTime(ctx)))
							.then(literal("getCoord").executes(ctx -> getCoord(ctx)))
							.then(literal("kickPassengers").executes(ctx -> kickPassengers(ctx)))
							.then(literal("resizeWindow")
									.then(argument("x", integer())
											.then(argument("y", integer()).executes(ctx -> resizeWindow(ctx)))))
							.then(literal("disconnect").executes(ctx -> disconnect(ctx)))
							.then(literal("calc")
									.then(argument("expression", greedyString()).executes(ctx -> calc(ctx))))
							));
	
		});
		
	}
	
	

}
