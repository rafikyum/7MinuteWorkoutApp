package com.example.a7minuteworkout

class Constants {

    companion object{
        fun defaultExerciseList(): ArrayList<ExerciseModel>{
            val exerciseList = ArrayList<ExerciseModel>()

            val jumpingJacks = ExerciseModel(1,
                "Jumping Jacks",
                R.drawable.ic_jumping_jacks,
                false,                      /* 1 */
                false)
            exerciseList.add(jumpingJacks)

            val wallSit = ExerciseModel(2,
                "Wall Sit",
                R.drawable.ic_wall_sit,
                false,                       /* 2 */
                false)
            exerciseList.add(wallSit)

            val pushUp = ExerciseModel(3,
                "Push Up",
                R.drawable.ic_push_up,
                false,                        /* 3 */
                false)
            exerciseList.add(pushUp)

            val abdominalCrunch =
                ExerciseModel(4,
                    "Abdominal Crunch",
                    R.drawable.ic_abdominal_crunch,
                    false,                   /* 4 */
                    false)
            exerciseList.add(abdominalCrunch)

            val stepUpOnChair =
                ExerciseModel(
                    5,
                    "Step-Up onto Chair",
                    R.drawable.ic_step_up_onto_chair,
                    false,                   /* 5 */
                    false
                )
            exerciseList.add(stepUpOnChair)

            val squat = ExerciseModel(6,
                "Squat",
                R.drawable.ic_squat,
                false,                      /* 6 */
                false)
            exerciseList.add(squat)

            val tricepDipOnChair =
                ExerciseModel(
                    7,
                    "Tricep Dip On Chair",
                    R.drawable.ic_triceps_dip_on_chair,
                    false,                      /* 7 */
                    false
                )
            exerciseList.add(tricepDipOnChair)

            val plank = ExerciseModel(8,
                "Plank",
                R.drawable.ic_plank,
                false,                          /* 8 */
                false)
            exerciseList.add(plank)

            val highKneesRunningInPlace =
                ExerciseModel(
                    9,
                    "High Knees Running In Place",
                    R.drawable.ic_high_knees_running_in_place,
                    false,                              /* 9 */
                    false
                )
            exerciseList.add(highKneesRunningInPlace)

            val lunges = ExerciseModel(10,
                "Lunges",
                R.drawable.ic_lunge,
                false,                          /* 10 */
                false)
            exerciseList.add(lunges)

            val pushupAndRotation =
                ExerciseModel(
                    11,
                    "Push up and Rotation",
                    R.drawable.ic_push_up_and_rotation,
                    false,                      /* 11 */
                    false
                )
            exerciseList.add(pushupAndRotation)

            val sidePlank = ExerciseModel(12,
                "Side Plank",
                 R.drawable.ic_side_plank,
                false,                      /* 12 */
                false)
            exerciseList.add(sidePlank)


            return exerciseList
        }
    }

}