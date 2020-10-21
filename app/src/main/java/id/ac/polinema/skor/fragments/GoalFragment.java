package id.ac.polinema.skor.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import id.ac.polinema.skor.R;
import id.ac.polinema.skor.databinding.FragmentGoalBinding;
import id.ac.polinema.skor.models.GoalScorer;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoalFragment extends Fragment {

	private String requestKey;
	private GoalScorer goalScorer;

	private FragmentGoalBinding binding;

	public GoalFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.goalScorer = new GoalScorer();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_goal, container, false);
		View view = binding.getRoot();
		binding.setGoalScorer(goalScorer);
		requestKey = GoalFragmentArgs.fromBundle(getArguments()).getRequestKey();

		binding.setFragment(this);

		return view;
	}

	public void onSaveClicked(View view) {
		Bundle bundle = new Bundle();
		bundle.putParcelable(ScoreFragment.SCORER_KEY, goalScorer);
		getParentFragmentManager().setFragmentResult(requestKey, bundle);
		Navigation.findNavController(view).navigateUp();
	}

	public void onCancelClicked(View view) {
		Navigation.findNavController(view).navigateUp();
	}
}