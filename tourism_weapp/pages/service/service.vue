<template>
	<view>
		<image class="haibao" src="https://ct.chuzhou.gov.cn/group4/M00/0D/4C/CpYIZmRu14WAaOW-AAFQsXK-oNA927.png" mode=""></image>
	
		<text class="h2 title">游玩保障服务</text>
		
		<view class="card_container">
			<view @click="callPhone('123456')" class="item" style="background: linear-gradient(to bottom,#07BCDA,#00D7C7 );">
				<view>
					<text class="h3">旅游咨询</text>
					<text>12301</text>
				</view>
				<view class="cuIcon-phone"></view>
			</view>
			<view @click="callPhone('12315')" class="item" style="background: linear-gradient(to bottom,#9C6DF7,#D780F1 );">
				<view>
					<text class="h3">投诉求助</text>
					<text>投诉有效 实时高效</text>
				</view>
				<view class="cuIcon-edit"></view>
			</view>
		</view>
		
		<text class="h2 title">旅游公共服务</text>
		
		<view class="card_container">
			<view @click="toTraffic" class="item" style="background: linear-gradient(to bottom,#FA8733,#FAA824);">
				<view>
					<text class="h3">公交信息</text>
					<text>公交线路查询</text>
				</view>
				<view class="cuIcon-deliver"></view>
			</view>
			<view class="item" style="background: linear-gradient(to bottom,#2FD162,#7ADF65);" @click="gotoWeather">
				<view>
					<text class="h3">一周天气</text>
					<text>一周天气 尽在掌握</text>
				</view>
				<view class=".cuIcon-favorfill"></view>
			</view>
		</view>
		
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			gotoWeather(){
				uni.navigateTo({
					url:'/pages/service/children/weather/weather'
				})
			},
			callPhone(phone){
				uni.makePhoneCall({
					phoneNumber: phone
				})
			},
			toTraffic(){
				uni.navigateTo({
					url:'/pages/service/children/traffic/traffic'
				})
			}
		}
	}
</script>

<style lang="scss">
	page{
		padding: 14px;
	}
	
	.haibao{
		width: 100%;
		height: 120px;
		border-radius: 10px;
	}
	
	.title{
		margin: 14px 0;
	}
	
	.card_container{
		display: grid;
		grid-template-columns: 1fr 1fr;
		grid-gap: 16px;
		
		.item{
			border-radius: 4px;
			padding: 10px;
			color: #FFF;
			display: grid;
			grid-template-columns: 1fr 40px;
			align-items: center;
			
			view:nth-child(2){
				background-color: rgba(255, 255, 255, 0.2);
				height: 40px;
				border-radius: 50%;
				text-align: center;
				line-height: 40px;
				font-size: 20px;
			}
		}
	}

</style>
