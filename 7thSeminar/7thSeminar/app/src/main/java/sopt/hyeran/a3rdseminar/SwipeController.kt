package sopt.hyeran.a3rdseminar

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.support.v7.widget.helper.ItemTouchHelper.*
import android.util.Log
import android.view.MotionEvent
import android.view.View

@SuppressLint("ClickableViewAccessibility")
class SwipeController() : ItemTouchHelper.Callback() {
    private var swipeBack = false
    private var buttonShowedState = ButtonState.GONE
    private var buttonInstance : RectF? = null
    private var currentItemViewHolder : RecyclerView.ViewHolder? = null
    private var buttonActions : SwipeControllerActions? = null

    constructor(buttonsActions : SwipeControllerActions?) : this(){
        this.buttonActions = buttonActions
    }

    private val buttonWidth = 300f
    val LOG = "Swipe"

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int {
        Log.v(LOG, "getMovementFlags")
        return ItemTouchHelper.Callback.makeMovementFlags(0, LEFT or RIGHT)
    }

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
        Log.v(LOG, "onMove")
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        Log.v(LOG, "onSwiped")
    }

    override fun convertToAbsoluteDirection(flags: Int, layoutDirection: Int): Int {
        Log.v(LOG, "convertToAbsoluteDirection1")
        if(swipeBack){
            Log.v(LOG, "converToAbsoluteDirection2")
            swipeBack = (buttonShowedState != ButtonState.GONE)
            return 0
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection)
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if (buttonShowedState == ButtonState.GONE) {
            Log.v(LOG, "onChildDraw4")
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
        currentItemViewHolder = viewHolder
    }
    private fun setTouchListener(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        recyclerView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                Log.v(LOG, "setTouchListener1")
                swipeBack = (event.action == MotionEvent.ACTION_CANCEL || event.action == MotionEvent.ACTION_UP)
                if (swipeBack) {
                    Log.v(LOG, "setTouchListener2")
                    if (dX < -buttonWidth)
                        buttonShowedState = ButtonState.RIGHT_VISIBLE
                    else if (dX > buttonWidth) buttonShowedState = ButtonState.LEFT_VISIBLE

                    if (buttonShowedState != ButtonState.GONE) {
                        Log.v(LOG, "setTouchListener3")
                        setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                        setItemsClickable(recyclerView, false)
                    }
                }
                return false
            }
        })
    }

    private fun setTouchDownListener(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        recyclerView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                Log.v(LOG, "setTouchDownListener1")
                if (event.action == MotionEvent.ACTION_DOWN) {
                    Log.v(LOG, "setTouchDownListener2")
                    setTouchUpListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }
                return false
            }
        })
    }

    private fun setTouchUpListener(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {


        recyclerView.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View, event: MotionEvent): Boolean {
                Log.v(LOG, "setTouchUpListener1")
                if (event.action == MotionEvent.ACTION_UP) {
                    Log.v(LOG, "setTouchUpListener2")
                    super@SwipeController.onChildDraw(c, recyclerView, viewHolder, 0f, dY, actionState, isCurrentlyActive)
                    recyclerView.setOnTouchListener(object : View.OnTouchListener {
                        override fun onTouch(v: View, event: MotionEvent): Boolean {
                            Log.v(LOG, "setTouchUpListener3")
                            return false
                        }
                    })
                    setItemsClickable(recyclerView, true)
                    swipeBack = false

                    if ((buttonActions != null) and (buttonInstance != null) and (buttonInstance!!.contains(event.x, event.y))) {
                        if (buttonShowedState == ButtonState.LEFT_VISIBLE) {
                            buttonActions!!.onLeftClicked(viewHolder.adapterPosition)
                        } else if (buttonShowedState == ButtonState.RIGHT_VISIBLE) {
                            buttonActions!!.onRightClicked(viewHolder.adapterPosition)
                        }
                    }
                    buttonShowedState = ButtonState.GONE
                    currentItemViewHolder = null
                }
                return false
            }
        })
    }

    private fun setItemsClickable(recyclerView: RecyclerView, isClickable: Boolean) {
        for (i in 0 until recyclerView.childCount) {
            recyclerView.getChildAt(i).isClickable = isClickable
        }
    }

    private fun drawButtons(c: Canvas, viewHolder: RecyclerView.ViewHolder) {
        val buttonWidthWithoutPadding = buttonWidth - 20
        val corners = 16f

        val itemView = viewHolder.itemView
        val p = Paint()

        val leftButton = RectF(itemView.left.toFloat(), itemView.top.toFloat(), itemView.left + buttonWidthWithoutPadding, itemView.bottom.toFloat())
        //pixel
        p.color = Color.BLUE
        c.drawRoundRect(leftButton, corners, corners, p)
        drawText("EDIT", c, leftButton, p)

        val rightButton = RectF(itemView.right - buttonWidthWithoutPadding, itemView.top.toFloat(), itemView.right.toFloat(), itemView.bottom.toFloat())
        p.color = Color.RED
        c.drawRoundRect(rightButton, corners, corners, p)
        drawText("DELETE", c, rightButton, p)

        buttonInstance = null
        if (buttonShowedState == ButtonState.LEFT_VISIBLE) {
            buttonInstance = leftButton
        } else if (buttonShowedState == ButtonState.RIGHT_VISIBLE) {
            buttonInstance = rightButton
        }
    }

    private fun drawText(text: String, c: Canvas, button: RectF, p: Paint) {
        val textSize = 60f//sp
        p.color = Color.WHITE
        p.isAntiAlias = true
        p.textSize = textSize

        val textWidth = p.measureText(text)
        c.drawText(text, button.centerX() - textWidth / 2, button.centerY() + textSize / 2, p)
    }

    fun onDraw(c: Canvas) {
        if (currentItemViewHolder != null) {
            drawButtons(c, currentItemViewHolder!!)
        }
    }
}