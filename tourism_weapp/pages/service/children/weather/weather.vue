<template>
	<view>
		<!-- 城市 -->
		<view class="text-xxl flex align-center justify-between">
			<view class="city">
				<text class="cuIcon-locationfill"></text>
				<text>滁州市</text>
			</view>
			<view class="refresh" @click="refresh">
				<text class="cuIcon-refresh"></text>
			</view>
		</view>
		
		<view class="margin-top-xl">
			<view class="text-center text-orange" style="font-size: 4rem;">{{nowWeather.temp}}°</view>
			<view class="flex align-center justify-center gap-sm text-df" style="gap: 14px;">
				<text>{{nowWeather.text || 0}}</text>
				<text>{{future7Weather[0].tempMin || 0}}° / {{future7Weather[0].tempMax || 0}}°</text>
				<text>{{nowWeather.windDir || 0}}</text>
			</view>
		</view>
		
		<view class="list flex flex-direction">
			<view class="item flex align-center margin-top-sm text-df" v-for="(item,index) in future7Weather" :key="index">
				<view>{{item.fxDate}}</view>
				<view class="flex-sub margin-left-xl">{{item.textDay}}</view>
				<view style="width: 70px; text-align: left;">{{item.windDirDay}}</view>
				<view class="margin-left-xl" style="width: 70px;text-align: right;">{{item.tempMin}}° / {{item.tempMax}}°</view>
			</view>
		</view>
	</view>
</template>

<script>
	import request from '@/utils/http.js'
	
	export default {
		data() {
			return {
				weatherApiKey: 'c2f1e0e8f892441ebda6d6b4a4618d8b',
				future7Weather: [],
				nowWeather: {}
			}
		},
		methods: {
			onLoad(){
				this.getWeatherData()
			},
			refresh(){
				this.getWeatherData()
			},
			getWeatherData(){
				uni.showLoading({})
				uni.request({
					url: 'https://devapi.qweather.com/v7/weather/now?location=101221101&key='+this.weatherApiKey,
					method: 'GET',
					showLoading: true,
					success: (res) => {
						this.nowWeather = res.data.now
					}
				})
				
				uni.request({
					url: 'https://devapi.qweather.com/v7/weather/7d?location=101221101&key='+this.weatherApiKey,
					method: 'GET',
					showLoading: true,
					success: (res) => {
						this.future7Weather = res.data.daily
						uni.hideLoading()
					}
				})
			}
		}
	}
</script>

<style lang="scss">
	page{
		padding: 20px;
	}
	
	.list{
		margin-top: 50px;
		background-color: #bcd7ee;
		padding: 10px;
		border-radius: 6px;
		gap: 14px;
	}
</style>
