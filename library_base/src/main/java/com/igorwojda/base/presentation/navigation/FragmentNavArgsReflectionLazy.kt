import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import com.igorwojda.base.presentation.navigation.NavArgsReflectionLazy

@MainThread
fun Fragment.navArgsReflection() =
    NavArgsReflectionLazy<NavArgs>(this::class) { arguments }
