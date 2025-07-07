# AccessibilityApp
This is a demonstration Android app built to showcase the use of **Accessibility Services** to detect user interactions in third-party VoIP apps like WhatsApp or Skype. The app simulates the logic required to trigger actions (like call recording) when a call is detected on the screen.
When ever any app performs action "Call" it simulates the recording of call on background
Logs all call recording events to Logcat

## Requirements

- Android 10 (API 29) or higher
- Accessibility permission must be manually granted
## How to Use

1. Install the APK on your Android device (API 29+)
2. Open the app and tap **“Open Accessibility Settings”**
3. Scroll to **Downloaded Services**
4. Tap **Accessibility Call Monitor** and enable it
5. Return to the app — if prompted, restart the app to activate the service
6. Open WhatsApp and initiate a call
7. The app logs events and simulates recording logic when call-related UI text is detected
## Sample Log Output

```plaintext
D/ACCESS_EVENT: Package: com.whatsapp, Text: Calling...
D/SIMULATION: Started simulated call recording

