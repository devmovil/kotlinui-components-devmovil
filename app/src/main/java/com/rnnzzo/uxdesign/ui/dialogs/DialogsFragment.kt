package com.rnnzzo.uxdesign.ui.dialogs

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.rnnzzo.uxdesign.R
import com.rnnzzo.uxdesign.databinding.FragmentDialogsBinding
import com.rnnzzo.uxdesign.databinding.LyBottomSheetBinding
import java.text.SimpleDateFormat
import java.util.*

class DialogsFragment: Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentDialogsBinding
    private val SEASONS = arrayOf("Spring","Summer","Fall","Winter")
    private val NUMBERS = arrayOf("One","Two","Three","Four","Five")
    private val numbers_state = BooleanArray(NUMBERS.size)
    private val dateFormat = SimpleDateFormat("MM-dd-yyyy")
    private val bottomSheetBehavior by lazy { BottomSheetBehavior.from(binding.lyBottomSheet) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogsBinding.inflate(layoutInflater)
        return binding.root
    }

    fun initEvents(){
        binding.btnDialogSimpleOne.setOnClickListener(this)
        binding.btnDialogSimpleTwo.setOnClickListener(this)
        binding.btnDialogSingleChoice.setOnClickListener(this)
        binding.btnDialogMultiChoice.setOnClickListener(this)
        binding.btnDialogDatePicker.setOnClickListener(this)
        binding.btnDialogTimePicker.setOnClickListener(this)
        binding.btnDialogProgress.setOnClickListener(this)
        binding.btnBottomSheet.setOnClickListener(this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initEvents()
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.btn_dialog_simple_one -> {
                showSimpleDialog()
            }
            R.id.btn_dialog_simple_two -> {
                showSimpleDialogWithTitle()
            }
            R.id.btn_dialog_single_choice -> {
                showSingleChoiceDialog()
            }
            R.id.btn_dialog_multi_choice -> {
                showMultiChoiceDialog()
            }
            R.id.btn_dialog_date_picker -> {
                showDatePickerDialog()
            }
            R.id.btn_dialog_time_picker -> {
                showTimePickerDialog()
            }
            R.id.btn_dialog_progress -> {
                showProgressDialog()
            }
            R.id.btn_bottom_sheet -> {
                showBottomSheetDialog()
            }
        }
    }

    fun showSimpleDialog(){
        val builder =
            AlertDialog.Builder(requireContext())
        builder.setMessage(R.string.small_lorem_ipsum)
        builder.setPositiveButton(R.string.OK,
             { dialogInterface, i ->
                Snackbar.make(
                    binding.containerFragmentDialogs,
                    "OK Clicked",
                    Snackbar.LENGTH_SHORT
                ).show()
            })
        builder.show()
    }

    fun showSimpleDialogWithTitle(){
        val builder =
            AlertDialog.Builder(requireContext())
        builder.setTitle("Dialog Title")
        builder.setMessage(R.string.small_lorem_ipsum)
        builder.setPositiveButton(R.string.OK,
            { dialogInterface, i ->
                Snackbar.make(
                    binding.containerFragmentDialogs,
                    "OK Clicked",
                    Snackbar.LENGTH_SHORT
                ).show()
            })
        builder.setNegativeButton(R.string.CANCEL,
            { dialogInterface, i ->
                Snackbar.make(
                    binding.containerFragmentDialogs,
                    "CANCEL Clicked",
                    Snackbar.LENGTH_SHORT
                ).show()
            })
        builder.show()
    }

    private var selectedSingleChoice = SEASONS[0]

    private fun showSingleChoiceDialog() {
        val builder =
            AlertDialog.Builder(requireContext())
        builder.setTitle("Season")
        builder.setSingleChoiceItems(
            SEASONS,
            0,
            { dialogInterface, i ->
                selectedSingleChoice = SEASONS[i]
            })
        builder.setPositiveButton(
            R.string.OK
        ) { dialogInterface, i ->
            Snackbar.make(
                binding.containerFragmentDialogs,
                "selected : $selectedSingleChoice",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton(R.string.CANCEL, null)
        builder.show()
    }

    private fun showMultiChoiceDialog() {
        val builder =
            AlertDialog.Builder(requireContext())
        builder.setTitle("Select some Numbers")
        builder.setMultiChoiceItems(
            NUMBERS,
            numbers_state,
            { dialogInterface, i, b ->
                numbers_state[i] = b
            })
        builder.setPositiveButton(
            R.string.OK
        ) { dialogInterface, i ->
            Snackbar.make(
                binding.containerFragmentDialogs,
                "Selected Numbers",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        builder.setNegativeButton(R.string.CANCEL, null)
        builder.show()
    }

    private fun showDatePickerDialog(){
        val calendar = Calendar.getInstance()
        DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            calendar.set(Calendar.MONTH, monthOfYear)
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            Snackbar.make(
                binding.containerFragmentDialogs,
                "${dateFormat.format(calendar.time)}",
                Snackbar.LENGTH_SHORT
            ).show()
        }, calendar[Calendar.YEAR], calendar[Calendar.MONTH], calendar[Calendar.DAY_OF_MONTH]).show()
    }

    private fun showTimePickerDialog(){
        val cal = Calendar.getInstance()
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)
            Snackbar.make(
                binding.containerFragmentDialogs,
                "${SimpleDateFormat("HH:mm").format(cal.time)}",
                Snackbar.LENGTH_SHORT
            ).show()
        }
        TimePickerDialog(requireContext(), timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
    }

    private fun showProgressDialog(){
        val progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Progress Bar")
        progressDialog.setMessage("Application is loading, please wait")
        progressDialog.show()
    }

    private fun showBottomSheetDialog(){

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        }

        val bottomSheetBinding = LyBottomSheetBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetBinding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bottomSheetDialog.getWindow()!!.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }

        bottomSheetBinding.btClose.setOnClickListener {
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

}