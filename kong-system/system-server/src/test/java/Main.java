/*
 * Copyright (c) 2024 Kong@杰少
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author FanWeiJie
 * @date 2023-09-15 21:43
 */
public class Main {

    public static void main(String[] args) {
        String str = "北京";
        String str2 = new String("北京");
        String str3 = str2;

        System.out.println(Integer.toHexString(System.identityHashCode(str)));
        System.out.println(Integer.toHexString(System.identityHashCode(str2)));
        System.out.println(Integer.toHexString(System.identityHashCode(str3)));

    }

}
