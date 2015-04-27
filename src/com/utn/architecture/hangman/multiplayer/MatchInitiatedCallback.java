package com.utn.architecture.hangman.multiplayer;

import android.os.RemoteException;
import android.service.carrier.CarrierMessagingService.ResultCallback;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult;

public class MatchInitiatedCallback implements
        ResultCallback<TurnBasedMultiplayer.InitiateMatchResult> {

    @Override
    public void onReceiveResult(InitiateMatchResult result)
            throws RemoteException {
        // Check if the status code is not success.
        Status status = result.getStatus();
        if (status.isSuccess()) {
            showError(status.getStatusCode());
            return;
        }

        TurnBasedMatch match = result.getMatch();

        // If this player is not the first player in this match, continue.
        if (match.getData() != null) {
            showTurnUI(match);
            return;
        }

        // Otherwise, this is the first player. Initialize the game state.
        initGame(match);

        // Let the player take the first turn
        showTurnUI(match);
    }

    private void initGame(TurnBasedMatch match) {
        // TODO Auto-generated method stub

    }

    private void showTurnUI(TurnBasedMatch match) {
        // TODO Auto-generated method stub

    }

    private void showError(int statusCode) {
        // TODO Auto-generated method stub

    }

}
