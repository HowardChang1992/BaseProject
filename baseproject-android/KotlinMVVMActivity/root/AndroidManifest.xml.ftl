<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application>
        <activity android:name="${packageName}.${activityClass}"
				  android:screenOrientation="portrait"
				  android:windowSoftInputMode="adjustPan"/>
    </application>

</manifest>