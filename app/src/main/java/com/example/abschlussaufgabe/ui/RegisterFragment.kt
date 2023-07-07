package com.example.abschlussaufgabe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.abschlussaufgabe.R
import com.example.abschlussaufgabe.databinding.FragmentRegisterBinding
import com.example.abschlussaufgabe.model.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

/**
 * Das Register Fragment Händelt das Register UI und die Logik von dem Registrieren mit Firebase
 */
class RegisterFragment : Fragment() {

    private val viewModel:MainViewModel by activityViewModels()

    private lateinit var binding: FragmentRegisterBinding

    private lateinit var auth: FirebaseAuth

    /**
     * die OnCreateView zeigt das UI Element an
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    /**
     * Die OnStart Funktion erstellt eine Firebase Instanz
     */
    override fun onStart() {
        super.onStart()
        auth = Firebase.auth
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Hier Setzten wir ein ClickListener und speichern uns den eingegebenen
         * Text ab
         */

        binding.btnRegister.setOnClickListener {
            val email = binding.tiEmail.text.toString()
            val passwort = binding.tiPasswort.text.toString()
            register(email,passwort)
        }
    }

    /**
     *  Hier überprüfen wir ob die Email und das Passwort felder Leer sind
     *  Sind sie leer geben wir ein Toast aus.
     */
    private fun register(email: String, passwort: String) {
        if (email.isEmpty()){
            Toast.makeText(this.context,"Bitte geben sie eine Email Adresse an", Toast.LENGTH_LONG).show()
        }

        if (passwort.isEmpty()){
            Toast.makeText(this.context,"Bitte geben Sie ein Passwort ein", Toast.LENGTH_LONG).show()
        }

        /**
         * Die Firebase funtkion zum Registrieren vom User
         */
        auth.createUserWithEmailAndPassword(email,passwort)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {

                    /**
                     * Ist das Registrieren  erfolgreich gewesen , UI wird geupdated mit dem Infos vom Users
                     * Anschließend kommt ein Toast mit erfolgreich Registriert
                     */
                    // TODO Überprüfung ob die Konstante genutzt werden muss
                    val user = auth.currentUser
                    findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
                    Toast.makeText(this.context,"Sie haben sich erfolgreich Registriert", Toast.LENGTH_LONG).show()
                } else {

                    /**
                     * Hat es nicht geklappt, wird ein Toast angezeigt das zugangsdaten nicht richtig sind.
                     */
                    Toast.makeText(this.context,"Sie konnten nicht Registriert werden.", Toast.LENGTH_LONG).show()
                }
            }
    }


}