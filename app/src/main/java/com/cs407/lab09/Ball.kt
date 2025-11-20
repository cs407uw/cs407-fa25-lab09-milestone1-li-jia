package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()// TODO: Call reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }
        val a0x = accX
        val a1x = xAcc
        val a0y = accY
        val a1y = yAcc

        val dx = velocityX * dT + (3f * a0x + a1x) * dT * dT / 6f
        val dy = velocityY * dT + (3f * a0y + a1y) * dT * dT / 6f

        posX += dx
        posY += dy

        velocityX += 0.5f * (a0x + a1x) * dT
        velocityY += 0.5f * (a0y + a1y) * dT


        accX = a1x
        accY = a1y


        checkBoundaries()
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        val minX = 0f
        val maxX = backgroundWidth - ballSize
        val minY = 0f
        val maxY = backgroundHeight - ballSize

        if (posX < minX) {
            posX = minX
            velocityX = 0f
            accX = 0f
        }
        if (posX > maxX) {
            posX = maxX
            velocityX = 0f
            accX = 0f
        }
        if (posY < minY) {
            posY = minY
            velocityY = 0f
            accY = 0f
        }
        if (posY > maxY) {
            posY = maxY
            velocityY = 0f
            accY = 0f
        } // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        posX = backgroundWidth / 2f - ballSize / 2f
        posY = backgroundHeight / 2f - ballSize / 2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
         // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
    }
}