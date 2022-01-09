package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.NonNull
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import kotlin.math.log
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            countLikes = 105,
            countShares = 145,
            countViews = 459_000

        )



        with(binding) {
            author.text = post.author
            content.text = post.content
            published.text = post.published
            textToShare.text = converterToShare(post)
            textCountViews.text = converterToCountViews(post)

            setLike(post)
            setCountLike(post)

            likesButton.setOnClickListener {
                post.likedByMe = !post.likedByMe
                setLike(post)
                setCountLike(post)
            }


            toShare.setOnClickListener {
                post.shareByMe
                post.countShares += 1
                textToShare.text = converterToShare(post)
            }
        }
    }

    private fun  ActivityMainBinding.setLike(post: Post) {
        likesButton.setImageResource(
            if (post.likedByMe) {
                R.drawable.ic_liked
            } else {
                R.drawable.ic_baseline_favorite_border_24
            }
        )
    }

    private fun  ActivityMainBinding.setCountLike(post: Post) {
        countToLike.text = if (post.likedByMe) {
            converterToLikes(post)
        } else {
            converterToDislikes(post)
        }
    }

    private fun converterToShare(post: Post): String {
       return when (post.countShares) {
           in 0..999 -> (post.countShares).toString()
           in 1_000..1_099 -> (post.countShares / 1_000.0.roundToInt()).toString() + "K"
           in 1_100..9_999 -> ((post.countShares / 1_000.0.roundToInt()) +
                   (post.countShares / 1_000.0.roundToInt()) / 10.0).toString() + "K"
           in 10_000..999_999 -> (post.countShares / 1_000.0.roundToInt()).toString() + "K"
           in 1_000_000..9_999_999 -> ((post.countShares / 1_000_000.0.roundToInt()) +
                   (post.countShares / 1_000_000.0.roundToInt()) / 10.0).toString() + "M"
           in 10_000_000..99_999_999 -> ((post.countShares / 1_000_000.0.roundToInt()) +
                   (post.countShares / 1_000_000.0.roundToInt()) / 100.0).toString() + "M"
           else -> ((post.countShares / 1_000_000.0.roundToInt()) +
                   (post.countShares / 1_000_000.0.roundToInt()) / 1000.0).toString() + "M"
       }
    }

    private fun converterToLikes(post: Post): String {
        return when (post.countLikes + 1) {
            in 0..999 -> (post.countLikes + 1).toString()
            in 1_000..1_099 -> ((post.countLikes + 1) / 1_000.0.roundToInt()).toString() + "K"
            in 1_100..9_999 -> (((post.countLikes + 1) / 1_000.0.roundToInt()) +
                    ((post.countLikes + 1) / 1_000.0.roundToInt()) / 10.0).toString() + "K"
            in 10_000..999_999 -> ((post.countLikes + 1) / 1_000.0.roundToInt()).toString() + "K"
            in 1_000_000..9_999_999 -> (((post.countLikes +1) / 1_000_000.0.roundToInt()) +
                    ((post.countLikes + 1) / 1_000_000.0.roundToInt()) / 10.0).toString() + "M"
            in 10_000_000..99_999_999 -> (((post.countLikes +1) / 1_000_000.0.roundToInt()) +
                    ((post.countLikes + 1) / 1_000_000.0.roundToInt()) / 100.0).toString() + "M"
            else -> (((post.countLikes + 1) / 1_000_000.0.roundToInt()) +
                    ((post.countLikes + 1) / 1_000_000.0.roundToInt()) / 1000.0).toString() + "M"
        }
    }

    private fun converterToDislikes(post: Post): String {
        return when (post.countLikes) {
            in 0..999 -> (post.countLikes).toString()
            in 1_000..1_099 -> (post.countLikes / 1_000.0.roundToInt()).toString() + "K"
            in 1_100..9_999 -> ((post.countLikes / 1_000.0.roundToInt()) +
                    (post.countLikes / 1_000.0.roundToInt()) / 10.0).toString() + "K"
            in 10_000..999_999 -> (post.countLikes / 1_000.0.roundToInt()).toString() + "K"
            in 1_000_000..9_999_999 -> ((post.countLikes / 1_000_000.0.roundToInt()) +
                    (post.countLikes / 1_000_000.0.roundToInt()) / 10.0).toString() + "M"
            in 10_000_000..99_999_999 -> ((post.countLikes / 1_000_000.0.roundToInt()) +
                    (post.countLikes / 1_000_000.0.roundToInt()) / 100.0).toString() + "M"
            else -> ((post.countLikes / 1_000_000.0.roundToInt()) +
                    (post.countLikes / 1_000_000.0.roundToInt()) / 10.0).toString() + "M"
        }
    }

    private fun converterToCountViews(post: Post): String {
        return when (post.countViews) {
            in 0..999 -> (post.countViews).toString()
            in 1_000..1_099 -> (post.countViews / 1_000.0.roundToInt()).toString() + "K"
            in 1_100..9_999 -> ((post.countViews / 1_000.0.roundToInt()) +
                    (post.countViews / 1_000.0.roundToInt()) / 10.0).toString() + "K"
            in 10_000..999_999 -> (post.countViews / 1_000.0.roundToInt()).toString() + "K"
            in 1_000_000..9_999_999 -> ((post.countViews / 1_000_000.0.roundToInt()) +
                    (post.countViews / 1_000_000.0.roundToInt()) / 10.0).toString() + "M"
            in 10_000_000..99_999_999 -> ((post.countViews / 1_000_000.0.roundToInt()) +
                    (post.countViews / 1_000_000.0.roundToInt()) / 100.0).toString() + "M"
            else -> ((post.countViews / 1_000_000.0.roundToInt()) +
                    (post.countViews / 1_000_000.0.roundToInt()) / 1000.0).toString() + "M"
        }
    }
}