package com.picatrix1899.steampunk.blocks;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.collect.Lists;

import net.minecraft.block.properties.PropertyEnum;

public class PropertyTier extends PropertyEnum<EnumTier>
{

	protected PropertyTier(String name, EnumTier... allowedTiers)
	{
		super(name, EnumTier.class, Lists.newArrayList(allowedTiers));
	}
}
