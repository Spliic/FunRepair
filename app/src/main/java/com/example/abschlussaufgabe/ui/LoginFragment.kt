package com.example.abschlussaufgabe.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.abschlussaufgabe.MainActivity
import com.example.abschlussaufgabe.R
import com.example.abschlussaufgabe.databinding.FragmentLoginBinding
import com.example.abschlussaufgabe.model.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


    /**
     * Das Login Fragment Händelt das Login UI und die Logik von dem Einloggen mit Firebase
    */

class LoginFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentLoginBinding

    private lateinit var auth: FirebaseAuth


    /**
     * Die OnStart Funktion überprüft ob User aktuell eingeloggt ist.
     * Wenn der User eingeloggt ist wird direkt zum HomeFragment Navigiert
     */
    override fun onStart() {
        super.onStart()
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
        }
    }

        /**
         * die OnCreateView zeigt das UI Element an
         */
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Hier Setzten wir ein ClickListener und speichern uns den eingegebenen
         * Text ab
         */
        binding.btnEinloggen.setOnClickListener {
            val email = binding.tiEmail.text.toString()
            val passwort = binding.tiPasswort.text.toString()
            login(email,passwort)
        }

        /**
         * Hier Navigieren wir vom LoginFragment zum RegisterFragment
         * wenn man auf dem Text Unterm Einlogg Button klickt.
         */
        binding.tvRegisterInLoginScreen.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment2())
        }
    }

        /**
         *  Hier überprüfen wir ob die Email und das Passwort felder Leer sind
         *  Sind sie leer geben wir ein Toast aus.
         */
    private fun login ( email:String , passwort: String) {
            if (email.isEmpty()) {
                Toast.makeText(
                    this.context,
                    "Bitte geben Sie eine Email Adresse an ",
                    Toast.LENGTH_LONG
                ).show()
            }

            if (passwort.isEmpty()) {
                Toast.makeText(this.context, "Bitte geben Sie ein Passwort ein", Toast.LENGTH_LONG)
                    .show()
            }


            /**
             * Firebase Funktion um den User einzuloggen
             */
            auth.signInWithEmailAndPassword(email, passwort)
                .addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        /**
                         * Ist das Einloggen erfolgreich gewesen , UI wird geupdated mit dem Infos vom Users
                         */
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                        val user = auth.currentUser
                    } else {
                        /**
                         * Hat es nicht geklappt, wird ein Toast angezeigt das zugangsdaten nicht richtig sind.
                         */
                        Toast.makeText(
                            this.context,
                            "Ihre Zugangsdaten sind nicht richtig.",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
}