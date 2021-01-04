#include<bits/stdc++.h>
using namespace std;
int main()
{
    int i=1,n,s=1;
cout<<"Enter value of n:";
cin>>n;
while(i<=n)
{s*=i;
i++;
}
cout<<s;
return 0;
}