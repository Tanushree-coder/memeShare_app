package com.example.quizapp

object Constants
{
    fun getQuestions(): ArrayList<Question>
    {
        val questionList=ArrayList<Question>()
        val que1=Question(
            1,R.drawable.flag_of_argentina,
            "What country does this flag belong to ?",
            "Argentina",
            "Australia",
            "Austria",
            "Armenia",
            1)
        questionList.add(que1)

        val que2=Question(
            2,R.drawable.flag_of_australia,
            "What country does this flag belong to ?",
            "USA",
            "Australia",
            "Austria",
            "Iceland",
            2)
        questionList.add(que2)

        val que3=Question(
            3,R.drawable.flag_of_belgium,
            "What country does this flag belong to ?",
            "Italy",
            "Mauritius",
            "Bahamas",
            "Belgium",
            4)
        questionList.add(que3)

        val que4=Question(
            4,R.drawable.flag_of_brazil,
            "What country does this flag belong to ?",
            "Brazil",
            "Gabon",
            "Fiji",
            "Kuwait",
            1)
        questionList.add(que4)

        val que5=Question(
            5,R.drawable.flag_of_denmark,
            "What country does this flag belong to ?",
            "Switzerland",
            "Dominica",
            "Denmark",
            "Poland",
            3)
        questionList.add(que5)

        val que6=Question(
            6,R.drawable.flag_of_france,
            "What country does this flag belong to ?",
            "Thailand",
            "Russia",
            "Romania",
            "France",
            4)
        questionList.add(que6)

        val que7=Question(
            7,R.drawable.flag_of_germany,
            "What country does this flag belong to ?",
            "Mexico",
            "Yemen",
            "Greece",
            "Germany",
            4)
        questionList.add(que7)

        val que8=Question(
            8,R.drawable.flag_of_india,
            "What country does this flag belong to ?",
            "India",
            "Ireland",
            "Côte d'Ivoire",
            "Niger",
            1)
        questionList.add(que8)

        val que9=Question(
            9,R.drawable.flag_of_ireland,
            "What country does this flag belong to ?",
            "Madagascar",
            "Hungary",
            "Ireland",
            "India",
            3)
        questionList.add(que9)

        val que10=Question(
            10,R.drawable.flag_of_italy,
            "What country does this flag belong to ?",
            "Colombia",
            "Italy",
            "Chile",
            "Guinea",
            2)
        questionList.add(que10)

        return questionList
    }
}
