package id.ac.polinema.skor.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import id.ac.polinema.skor.R;
import id.ac.polinema.skor.databinding.FragmentScoreBinding;
import id.ac.polinema.skor.models.GoalScorer;

public class ScoreFragment extends Fragment {

	public static final String HOME_REQUEST_KEY = "home";
	public static final String AWAY_REQUEST_KEY = "away";
	public static final String SCORER_KEY = "scorer";
	private static Object GoalFragment;

	private List<GoalScorer> homeGoalScorerList;
	private List<GoalScorer> awayGoalScorerList;

	public ScoreFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.homeGoalScorerList = new ArrayList<>();
		this.awayGoalScorerList = new ArrayList<>();
	}
	//menampung hasil data kembalian dari Fragment GoalFragment
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		FragmentScoreBinding binding = DataBindingUtil
				.inflate(inflater, R.layout.fragment_score, container, false);
		binding.setHomeGoalScorerList(homeGoalScorerList);
		binding.setAwayGoalScorerList(awayGoalScorerList);
		binding.setFragment(this);
		View view = binding.getRoot();

		getParentFragmentManager().setFragmentResultListener(HOME_REQUEST_KEY, this, new FragmentResultListener() {
			@Override
			public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
				GoalScorer goalScorer = result.getParcelable(SCORER_KEY);
				homeGoalScorerList.add(goalScorer);
			}
		});

		getParentFragmentManager().setFragmentResultListener(AWAY_REQUEST_KEY, this, new FragmentResultListener() {
			@Override
			public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
				GoalScorer goalScorer = result.getParcelable(SCORER_KEY);
				awayGoalScorerList.add(goalScorer);
			}
		});
		return view;
	}

	public String home(){
		StringBuilder builder = new StringBuilder();
		for (GoalScorer goalScorer : homeGoalScorerList){
			builder.append(goalScorer.getName())
					.append(" ")
					.append(goalScorer.getMinute())
					.append("\" ");
		}
		return builder.toString();
	}

	public String away(){
		StringBuilder builder = new StringBuilder();
		for (GoalScorer goalScorer : awayGoalScorerList){
			builder.append(goalScorer.getName())
					.append(" ")
					.append(goalScorer.getMinute())
					.append("\" ");
		}
		return builder.toString();
	}

	//berisi logika untuk berpindah ke GoalFragment. Pada perpindahan ini dikirimkan data tim home menggunakan konstanta HOME_REQUEST_KEY.
	public void onAddHomeClick(View view) {
		ScoreFragmentDirections.GoalScorerAction action = ScoreFragmentDirections.goalScorerAction(HOME_REQUEST_KEY);
		Navigation.findNavController(view).navigate(action);
	}

	public void onAddAwayClick(View view) {
		ScoreFragmentDirections.GoalScorerAction action = ScoreFragmentDirections.goalScorerAction(AWAY_REQUEST_KEY);
		Navigation.findNavController(view).navigate(action);
	}

}