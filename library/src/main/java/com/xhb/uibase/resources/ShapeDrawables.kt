package com.xhb.uibase.resources

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat

class ShapeDrawables {

    data class Config (val shape: Int,
                       @DimenRes val radius: Int, @ColorRes val fillColor: Int,
                       @DimenRes val borderSize: Int, @ColorRes val borderColor: Int,
                       @DimenRes val width: Int, @DimenRes val height: Int)

    data class RawConfig (val shape: Int,
                       val radius: Float, val fillColor: ColorStateList?,
                       val borderSize: Int, val borderColor: ColorStateList?,
                       val width: Int, val height: Int)

    companion object {

        private val drawables: MutableMap<Config, GradientDrawable> = mutableMapOf()

        fun getDrawable(context: Context, config: Config) : GradientDrawable {
//            return drawables.getOrPut(config) {
//                return createDrawable(context, config)
//            }
            var drawable = drawables.get(config)
            if (drawable == null) {
                drawable = createDrawable(context, config)
                if (!drawable.isStateful())
                    drawables.put(config, drawable)
            }
            return drawable
        }

        fun createDrawable(context: Context, config: Config) : GradientDrawable {
            val drawable = GradientDrawable()
            drawable.shape = config.shape
            if (config.width != 0 && config.height != 0) {
                val width = context.resources.getDimensionPixelSize(config.width)
                val height = context.resources.getDimensionPixelSize(config.height)
                drawable.setSize(width, height)
            }
            if (config.radius > 0)
                drawable.cornerRadius = context.resources.getDimension(config.radius)
            drawable.color = ContextCompat.getColorStateList(context, config.fillColor)
            if (config.borderSize != 0) {
                drawable.setStroke(context.resources.getDimensionPixelSize(config.borderSize),
                    ContextCompat.getColorStateList(context, config.borderColor))
            }
            return drawable
//            return DrawableBuilder().apply {
//                shape(config.shape)
//                val width = context.resources.getDimensionPixelSize(config.width)
//                val height = context.resources.getDimensionPixelSize(config.height)
//                size(width, height)
//                cornerRadius(context.resources.getDimensionPixelSize(config.radius))
//                solidColorStateList(context.resources.getColorStateList(config.fillColor))
//                //strokeWidth(context.resources.getDimensionPixelSize(config.borderSize))
//                strokeWidth(2)
//                strokeColorStateList(context.resources.getColorStateList(config.borderColor))
//            }.build()
        }

        fun createDrawable(config: RawConfig) : GradientDrawable {
            val drawable = GradientDrawable()
            drawable.shape = config.shape
            if (config.width != 0 && config.height != 0) {
                drawable.setSize(config.width, config.height)
            }
            if (config.radius > 0)
                drawable.cornerRadius = config.radius
            drawable.color = config.fillColor
            if (config.borderSize != 0) {
                drawable.setStroke(config.borderSize, config.borderColor)
            }
            return drawable
        }
    }

}