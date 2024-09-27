import React, {useEffect, useState} from "react";
import { StyleSheet, View } from "react-native";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { Magnetometer } from "expo-sensors";
import Svg, {Circle, Line, Text} from 'react-native-svg';

const Stack = createNativeStackNavigator();

function CompassScreen() {
  const [magnetometerData, setMagnetometerData] = useState({});
  const [heading, setHeading] = useState(0);

  useEffect(() => {
    const subscription = Magnetometer.addListener(data => {
      setMagnetometerData(data);
      const x = data.x;
      const y = data.y;
      const angle  = Math.atan2(y, x) * 180 / Math.PI;
      setHeading(angle >= 0 ? angle : angle + 360);
    })
    return() => {
      subscription.remove();
    }
  }, []);

  return (
    <View style></View>
  );
}