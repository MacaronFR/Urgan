package fr.imacaron.groupe19.urgan.login

import android.Manifest
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import fr.imacaron.groupe19.urgan.R
import fr.imacaron.groupe19.urgan.databinding.FragmentLoginBinding
import fr.imacaron.groupe19.urgan.home.HomeActivity

class LoginFragment: Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private var notificationId = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.forgot.setOnClickListener {
            findNavController().navigate(R.id.ForgotFragment)
        }

        binding.login.setOnClickListener {
            val intent = Intent(this.requireContext(), HomeActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent = PendingIntent.getActivity(this.requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE)
            val builder = NotificationCompat.Builder(this.requireContext(), "Bonjour")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Connecté")
                .setContentText("Vous êtes connecter")
                .setStyle(NotificationCompat.BigTextStyle().bigText("Vous vous êtes connecter à l'application urgan l'homme goujon avec le compte TODO"))
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
            with(NotificationManagerCompat.from(this.requireContext())){
                if (ActivityCompat.checkSelfPermission(
                        this@LoginFragment.requireContext(),
                        Manifest.permission.POST_NOTIFICATIONS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(this@LoginFragment.requireActivity(), arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
                }else{
                    notify(notificationId, builder.build())
                    notificationId++
                }
            }
        }
    }
}
