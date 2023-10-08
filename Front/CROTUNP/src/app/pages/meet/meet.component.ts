import { Component, OnInit } from '@angular/core';
import { VideoSDKMeeting } from '@videosdk.live/rtc-js-prebuilt';



@Component({
  selector: 'vex-meet',
  templateUrl: './meet.component.html',
  styleUrls: ['./meet.component.scss']
})
export class MeetComponent implements OnInit {


    async ngOnInit() {
      const apiKey = 'fc2654d0-442b-4890-89dc-5644eedee19b';
      const meetingId = 'milkyway';
      const name = 'Demo User';

      const config = {
        name: name,
        meetingId: meetingId,
        apiKey: apiKey,

        containerId: null,
        redirectOnLeave: 'https://www.videosdk.live/',

        micEnabled: true,
        webcamEnabled: true,
        participantCanToggleSelfWebcam: true,
        participantCanToggleSelfMic: true,

        chatEnabled: true,
        screenShareEnabled: true,
        pollEnabled: true,
        whiteboardEnabled: true,
        raiseHandEnabled: true,

        recordingEnabled: true,
        recordingEnabledByDefault: false,
        recordingWebhookUrl: 'https://www.videosdk.live/callback',
        recordingAWSDirPath: `/meeting-recordings/${meetingId}/`, // automatically save recording in this s3 path


      };

      const meeting = new VideoSDKMeeting();
      meeting.init();
    }

}
